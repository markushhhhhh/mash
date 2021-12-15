package com.example.artistapi.services;

import com.example.artistapi.apis.WikipediaApi;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WikipediaService {

    @Autowired
    WikipediaApi wpApi;


    public String getDescription(String title) {
        // Calling WP API
        JsonNode wpResponse = wpApi.callWikipedia(title);
        if(wpResponse == null){
            return null;
        }
        return wpResponse.findPath("extract").asText();
    }

}
