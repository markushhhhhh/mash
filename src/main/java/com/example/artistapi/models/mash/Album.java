package com.example.artistapi.models.mash;

import java.net.URL;

public class Album {
    
    private String title;
    private String id;
    private URL image;
    
    public Album(){}

    public Album(String title, String id, URL imgurl) {
        this.title =  title;
        this.id = id;
        this.image = imgurl;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public URL getImage() {
        return image;
    }
    public void setImage(URL image) {
        this.image = image;
    }
    
}
