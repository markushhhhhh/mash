package com.example.artistapi.apis;

import com.fasterxml.jackson.databind.JsonNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WikipediaApi {

    private static Logger LOGGER = LoggerFactory.getLogger(WikiDataApi.class);

    @Value("${wp.endpoint}")
    private String wpEndpoint;

    @Cacheable("wikipedia")
    public JsonNode callWikipedia(String title) {
        
        LOGGER.info("Calling Wikipedia API");
        WebClient wc = WebClient.create();
        JsonNode wpData;
        try {
            wpData = wc.get()
                    .uri(wpEndpoint, title)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve().bodyToMono(JsonNode.class)
                    .block();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
        return wpData;
    }

}
