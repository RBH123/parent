package ruan.provider.elasticsearch;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ruan.provider.constant.CommonConstant;
import ruan.provider.util.ObjectUtil;

@Component
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ElasticsearchUtil {

    private final RestHighLevelClient restHighLevelClient;

    @SneakyThrows
    public void createIndex(String index, Class cls,int shardsCnt,int replicasCnt){
        CreateIndexRequest request = new CreateIndexRequest(index);
        Object obj = cls.newInstance();
        XContentBuilder builder = ObjectUtil.obj2XContentBuilder(obj, "yyyy-MM-dd");
        if(builder != null){
            request.mapping(builder);
        }
        request.settings(Settings.builder().put(CommonConstant.INDEX_NUMBER_OF_SHARDS,shardsCnt).put(
                CommonConstant.INDEX_NUMBER_OF_REPLICAS,replicasCnt));
        restHighLevelClient.indices().create(request,RequestOptions.DEFAULT);
    }

    public static void createMapping(){

    }
}