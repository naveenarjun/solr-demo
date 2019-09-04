package com.test.solr.config;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SolrProperties.class)
public class SolrConfig {

    @Autowired
    private SolrProperties solrProperties;

    // make use of the bound properties
    @Bean
    @ConditionalOnProperty(prefix = "app.indexer" , name = "enabled", havingValue = "solr")
    @ConditionalOnMissingBean
    public HttpSolrClient solrClient() {
        String urlString = solrProperties.getServername();
        HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
        solr.setParser(new XMLResponseParser());

        return solr;
    }
}