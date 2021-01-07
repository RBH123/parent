package ruan.provider.elasticsearch;


import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruan.provider.constant.CommonConstant;
import ruan.provider.elasticsearch.common.EsAsyncAction;
import ruan.provider.elasticsearch.pojo.MatchingParam;
import ruan.provider.util.ObjectUtil;
import ruan.provider.util.SnowflakesUtil;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ElasticsearchUtil {

    private final RestHighLevelClient restHighLevelClient;
    private final EsAsyncAction esAsyncAction;

    /**
     * 创建索引
     *
     * @param index
     * @param cls
     * @param shardsCnt
     * @param replicasCnt
     */
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

    /**
     * 添加数据
     *
     * @param list
     * @param index
     * @param <T>
     */
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

    /**
     * 查询操作
     *
     * @param t
     * @param paramList
     * @param index
     * @param <T>
     * @return
     */
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
        searchRequest.indices(index);
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

    /**
     * 更新操作
     *
     * @param t
     * @param index
     * @param id
     * @param <T>
     */
    public <T> void update(T t, String index, String id) {
        try {
            UpdateRequest updateRequest = new UpdateRequest(index, id);
            Map<String, Object> map = ObjectUtil.object2Map(t);
            updateRequest.doc(map);
            UpdateResponse update = restHighLevelClient
                    .update(updateRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("更新失败:{}", e);
        }
    }

    /**
     * 删除数据
     *
     * @param index
     * @param id
     */
    public void delete(String index, String id) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(index, id);
            restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("删除数据异常:{}", e);
        }
    }

    /**
     * 根据查询条件删除
     *
     * @param paramList
     * @param index
     */
    public void deleteBySearch(List<MatchingParam> paramList, String index) {
        if (CollectionUtils.isEmpty(paramList)) {
            return;
        }
        try {
            BoolQueryBuilder builder = new BoolQueryBuilder();
            paramList.forEach(matchingParam -> {
                if (matchingParam.isFuzzy()) {
                    MatchQueryBuilder matchQueryBuilder =
                            new MatchQueryBuilder(matchingParam.getField(),
                                    matchingParam.getParam());
                    builder.must(matchQueryBuilder);
                } else {
                    TermQueryBuilder termQueryBuilder = new TermQueryBuilder(
                            matchingParam.getField(),
                            matchingParam.getParam());
                    builder.must(termQueryBuilder);
                }
            });
            DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(index);
            deleteByQueryRequest.setQuery(builder);
            restHighLevelClient
                    .deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("删除数据异常:{}", e);
        }
    }

    /**
     * 根据查询条件进行更新操作
     *
     * @param paramList
     * @param index
     */
    public void updateBySearch(List<MatchingParam> paramList, String index) {
        if (CollectionUtils.isEmpty(paramList)) {
            return;
        }
        try {
            BoolQueryBuilder builder = new BoolQueryBuilder();
            paramList.forEach(matchingParam -> {
                if (matchingParam.isFuzzy()) {
                    MatchQueryBuilder matchQueryBuilder =
                            new MatchQueryBuilder(matchingParam.getField(),
                                    matchingParam.getParam());
                    builder.must(matchQueryBuilder);
                } else {
                    TermQueryBuilder termQueryBuilder = new TermQueryBuilder(
                            matchingParam.getField(),
                            matchingParam.getParam());
                    builder.must(termQueryBuilder);
                }
            });
            UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest(index);
            updateByQueryRequest.setQuery(builder);
            restHighLevelClient.updateByQueryAsync(updateByQueryRequest, RequestOptions.DEFAULT,
                    esAsyncAction);
        } catch (Exception e) {
            log.error("操作es异常:{}", e);
        }
    }

    public static double average(String field){
        AggregationBuilders.avg("avg").field("")
    }
}