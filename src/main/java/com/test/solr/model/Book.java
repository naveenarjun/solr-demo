package com.test.solr.model;

import com.test.solr.helper.SolrPartialDocument;
import org.apache.solr.client.solrj.beans.Field;

import java.util.HashMap;

public class Book extends SolrPartialDocument {

    @Field("id")
    private String id;

    @Field("cat")
    private String cat;

    @Field("name")
    private String name;

    public Book(String id, HashMap<String,FieldModifier> fieldsMap) {
        super(id, fieldsMap);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}