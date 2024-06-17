package com.msp.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;


@Configuration
@EnableElasticsearchAuditing
@EnableElasticsearchRepositories
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/vnd.elasticsearch+json;compatible-with=7");
        headers.add("Content-Type", "application/vnd.elasticsearch+json;compatible-with=7");
        ClientConfiguration clientConfig = ClientConfiguration.builder()
                .connectedToLocalhost().
                withDefaultHeaders(headers)
                .build();
        return RestClients.create(clientConfig).rest();
    }
}
