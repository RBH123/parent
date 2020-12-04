package ruan.provider.elasticsearch;


import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruan.provider.constant.CommonConstant;
import ruan.provider.elasticsearch.pojo.MatchingParam;
import ruan.provider.util.ObjectUtil;
import ruan.provider.util.SnowflakesUtil;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ElasticsearchUtil {

    private final RestHighLevelClient restHighLevelClient;

    @SneakyThrows
    public void createIndex(String index, Class cls, int shardsCnt, int replicasCnt) {
        CreateIndexRequest request = new CreateIndexRequest(index);
        Object obj = cls.newInstance();
        XContentBuilder builder = ObjectUtil.obj2XContentBuilder(obj, "yyyy-MM-dd");
        if (builder != null) {
            request.mapping(builder);
        }
        request.settings(
                Settings.builder().put(CommonConstant.INDEX_NUMBER_OF_SHARDS, shardsCnt).put(
                        CommonConstant.INDEX_NUMBER_OF_REPLICAS, replicasCnt));
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }

    public <T> void putData(List<T> list, String index) {
        try {
            BulkRequest bulkRequest = new BulkRequest();
            list.parallelStream().forEach(i -> {
                IndexRequest indexRequest = new IndexRequest(index);
                indexRequest.source(JSON.toJSONString(i), XContentType.JSON);
                indexRequest.id(String.valueOf(SnowflakesUtil.INSTANCE().nextId()));
                bulkRequest.add(indexRequest);
            });
            restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    @SneakyThrows
    public <T> List<T> fuzzyMatching(T t, List<MatchingParam> paramList, String index) {
        if (CollectionUtils.isEmpty(paramList)) {
            return Collections.EMPTY_LIST;
        }
        BoolQueryBuilder builder = new BoolQueryBuilder();
        paramList.forEach(matchingParam -> {
            if (matchingParam.isFuzzy()) {
                MatchQueryBuilder matchQueryBuilder =
                        new MatchQueryBuilder(matchingParam.getField(), matchingParam.getParam());
                builder.must(matchQueryBuilder);
            } else {
                TermQueryBuilder termQueryBuilder = new TermQueryBuilder(matchingParam.getField(),
                        matchingParam.getParam());
                builder.must(termQueryBuilder);
            }
        });
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(builder);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        List<SearchHit> searchHits = Arrays.asList(hits);
        List<T> list = searchHits.stream().map(s -> {
            String source = s.getSourceAsString();
            if (StringUtils.isNotBlank(source)) {
                Object object = JSON.parseObject(source, t.getClass());
                T t1 = (T) object;
                return t1;
            }
            return null;
        }).filter(s -> s != null).collect(Collectors.toList());
        return list;
    }
}