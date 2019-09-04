package com.test.solr.helper;

import com.test.solr.model.FieldModifier;
import org.apache.solr.common.SolrInputDocument;

import java.util.HashMap;
import java.util.Map;

public abstract class  SolrPartialDocument {

    String id;

    private final HashMap<String,FieldModifier> fieldsMap;
    public SolrPartialDocument(String id, HashMap<String,FieldModifier> fieldsMap) {
        this.id = id;
        this.fieldsMap = fieldsMap;
    }

    public SolrInputDocument converToSolrDoc() {
        SolrInputDocument solrDoc = new SolrInputDocument();

        for (Map.Entry<String,FieldModifier> entry : fieldsMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            solrDoc.addField(entry.getKey(), entry.getValue().getFieldModifier());  // add the map as the field value
        }
        return solrDoc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, FieldModifier> getFieldsMap() {
        return fieldsMap;
    }


}
