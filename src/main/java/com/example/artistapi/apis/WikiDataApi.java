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
public class WikiDataApi {

    private static Logger LOGGER = LoggerFactory.getLogger(WikiDataApi.class);

    @Value("${wd.endpoint}")
    private String wdEndpoint;

    @Value("${wd.lang}")
    private String wdLang;

    @Cacheable("wikidata")
    public JsonNode callWikiData(String entity) {
        LOGGER.info("Calling Wikidata API");
        WebClient wc = WebClient.create();

        JsonNode wdData;
        try {
            wdData = wc.get()
                    .uri(wdEndpoint, entity)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve().bodyToMono(JsonNode.class)
                    .block();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }

        LOGGER.info("exiting getWikiData api service");
        return wdData;
    }

}
