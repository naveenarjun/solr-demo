package com.test.solr.service;

import com.test.solr.helper.SolrPartialDocument;
import org.springframework.messaging.support.GenericMessage;

import java.util.Collection;

public interface ISearchIndexer {
    void add(GenericMessage message);
    void add(Collection<GenericMessage> messages);

    void updatePartial(SolrPartialDocument document);
    void updatePartial(Collection<SolrPartialDocument> documents);

}
