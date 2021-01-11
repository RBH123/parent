package ruan.provider.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestClientBuilder.RequestConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * es客户端配置
 */
@Slf4j
@Configuration
public class ElasticsearchConfig {

    @Autowired
    private EsProperties esProperties;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder restClientBuilder =
                RestClient.builder(new HttpHost(esProperties.getHost(), esProperties.getPort(),
                        esProperties.getSchema()));
        restClientBuilder.setRequestConfigCallback(
                new RequestConfigCallback() {
                    @Override
                    public Builder customizeRequestConfig(Builder builder) {
                        builder.setConnectionRequestTimeout(esProperties.getConnectRequestTimeOut());
                        builder.setConnectTimeout(esProperties.getConnectTimeOut());
                        builder.setSocketTimeout(esProperties.getSocketTimeOut());
                        return builder;
                    }
                });
        restClientBuilder.setHttpClientConfigCallback(
                new HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(
                            HttpAsyncClientBuilder httpAsyncClientBuilder) {
                        httpAsyncClientBuilder.setMaxConnTotal(esProperties.getMaxConnectNum());
                        httpAsyncClientBuilder
                                .setMaxConnPerRoute(esProperties.getMaxConnectPerRoute());
                        return httpAsyncClientBuilder;
                    }
                });
        return new RestHighLevelClient(restClientBuilder);
    }
}
