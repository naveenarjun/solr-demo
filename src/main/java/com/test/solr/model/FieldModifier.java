package com.test.solr.model;

import org.apache.solr.common.util.Hash;

import javax.print.attribute.HashAttributeSet;
import java.util.HashMap;
import java.util.Map;

public class FieldModifier {

    /**
    Column to be updated
     */
    HashMap<String, Object> fieldModifier;

    /**
     * Modifier with operation and the new value of the field
     */

    // TODO solrOperation key needs to be changed to Enum
    public FieldModifier(String solrOperation, Object data) {
        fieldModifier = new HashMap<String, Object>();
        fieldModifier.put(solrOperation, data);
    }

    public HashMap<String, Object> getFieldModifier() {
        return fieldModifier;
    }

}
