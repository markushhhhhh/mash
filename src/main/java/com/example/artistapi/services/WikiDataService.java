package com.example.artistapi.services;

import java.net.URL;

import com.example.artistapi.apis.WikiDataApi;
import com.example.artistapi.models.mb.MbData;
import com.example.artistapi.models.mb.Relation;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WikiDataService {

    @Value("${wd.lang}")
    private String wdLang;

    @Autowired
    WikiDataApi wdApi;

    public String getTitle(MbData mbData, String wd) {
        // Get relation related to wikidata
        Relation wikiDataRelation = getWikiDataRelation(mbData, wd);
        // Get URL holding the entity
        if (wikiDataRelation == null) {
            return null;
        }
        URL url = wikiDataRelation.getUrl().getResource();
        // Get last segment of URL
        // Improvement - better solution
        String[] segments = url.getPath().split("/");
        String entity = segments[segments.length - 1];
        // get title of artist in eng language
        String title = getTitle(entity);
        return title;
    }

    private Relation getWikiDataRelation(MbData mbData, String wd) {
        return mbData.getRelations().stream()
                .filter(relation -> relation.getType().equals(wd))
                .findFirst()
                .orElse(null);
    }

    private String getTitle(String entity) {
        // Calling wikidata
        JsonNode wdResponse = wdApi.callWikiData(entity);
        if (wdResponse == null) {
            return null;
        }
        JsonNode site = wdResponse.findValue(wdLang);
        if (wdResponse.findValue(wdLang) == null) {
            return null;
        }
        String title = site.get("title").asText();
        if (title == null) {
            return null;
        }
        return title;
    }
}
