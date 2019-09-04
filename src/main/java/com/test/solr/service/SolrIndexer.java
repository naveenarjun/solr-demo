package com.test.solr.service;

import com.test.solr.helper.SolrPartialDocument;
import com.test.solr.model.Book;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@ConditionalOnProperty(prefix = "app.indexer" , name = "enabled", havingValue = "solr")
public class SolrIndexer implements ISearchIndexer {

    @Autowired
    HttpSolrClient solrClient;

    @Override
    public void add(GenericMessage message) {
        Book book = (Book) message.getPayload();
        System.out.println("solr indexer enabled");
        System.out.println("solrclient"+solrClient.getBaseURL());
        try {
            solrClient.addBean(book);
            solrClient.commit();
        } catch (Exception e){

        }

    }

    @Override
    public void add(Collection<GenericMessage> messages) {
        Collection books = new ArrayList<>();
        for (GenericMessage temp : messages) {
            books.add(temp.getPayload());
        }

        System.out.println("solr BULK indexer enabled");
        System.out.println("solrclient"+solrClient.getBaseURL());
        try {
            solrClient.addBeans(books);
            solrClient.commit();
        } catch (Exception e){

        }
    }

    @Override
    public void updatePartial(SolrPartialDocument document) {
        SolrInputDocument  sdoc = document.converToSolrDoc();
        try {
            solrClient.add(sdoc);
            solrClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePartial(Collection<SolrPartialDocument> documents) {

    }
}