package com.example.artistapi.models.mb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Relation {
    private String type;
    private EntityUrl url;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public EntityUrl getUrl() {
        return url;
    }
    public void setUrl(EntityUrl url) {
        this.url = url;
    }
}
