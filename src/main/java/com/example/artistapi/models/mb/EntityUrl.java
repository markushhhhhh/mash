package com.example.artistapi.models.mb;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityUrl {
    private URL resource;

    public URL getResource() {
        return resource;
    }
    public void setResource(URL resource) {
        this.resource = resource;
    }
}
