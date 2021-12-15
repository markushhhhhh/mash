package com.example.artistapi.models.mb;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MbData {
    private String name;
    @JsonProperty("release-groups")
    private List<ReleaseGroup> releaseGroups;
    private List<Relation> relations;

    public MbData(){}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<ReleaseGroup> getReleaseGroups() {
        return releaseGroups;
    }
    public void setReleaseGroups(ArrayList<ReleaseGroup> releaseGroups) {
        this.releaseGroups = releaseGroups;
    }
    public List<Relation> getRelations() {
        return relations;
    }
    public void setRelations(ArrayList<Relation> relations) {
        this.relations = relations;
    }

}
