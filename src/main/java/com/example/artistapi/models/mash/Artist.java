package com.example.artistapi.models.mash;

import java.util.ArrayList;
import java.util.List;

public class Artist {

    private String mbid;
    private String description;
    private List<Album> albums;

    public Artist(){}

    public String getMbid() {
        return mbid;
    }
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Album> getAlbums() {
        return albums;
    }
    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

}