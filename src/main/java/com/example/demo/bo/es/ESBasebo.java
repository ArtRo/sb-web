package com.example.demo.bo.es;

/**
 * Created by dhf_ndm on 2019/5/8
 * the previous bug derived from the previous
 */
public class ESBasebo {

    String index;
    String type;
    String id;
    Object document;

    String[] includes;

    String[] excludes;

    String[] storeFields;


    public ESBasebo() {
    }

    public ESBasebo(String index, String type, String id, Object document) {
        this.index = index;
        this.type = type;
        this.id = id;
        this.document = document;
    }

    public String[] getStoreFields() {
        return storeFields;
    }

    public void setStoreFields(String[] storeFields) {
        this.storeFields = storeFields;
    }

    public String[] getIncludes() {
        return includes;
    }

    public void setIncludes(String[] includes) {
        this.includes = includes;
    }

    public String[] getExcludes() {
        return excludes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getDocument() {
        return document;
    }

    public void setDocument(Object document) {
        this.document = document;
    }
}
