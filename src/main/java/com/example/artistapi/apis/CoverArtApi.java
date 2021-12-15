package com.example.artistapi.apis;

import java.time.Duration;

import com.example.artistapi.exceptions.NoCoverArtException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.timeout.TimeoutException;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Service
public class CoverArtApi {

    private static Logger LOGGER = LoggerFactory.getLogger(CoverArtApi.class);

    @Value("${ca.endpoint}")
    private String caEndpoint;

    HttpClient client = HttpClient.create()
            .followRedirect(true);

    WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(client))
            .build();

    @Cacheable("coverart")
    public String callCoverArt(String id) {
        LOGGER.info("Calling CAA API");
        String caData;
        try {
        caData = webClient.get()
                .uri(caEndpoint, id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(status -> status.equals(HttpStatus.NOT_FOUND),
                        response -> Mono.error(new NoCoverArtException("No cover art found for album: " + id)))
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(4))
                .retry(2)
                .block();
        }
        catch (NoCoverArtException e) {
            LOGGER.info(e.getMessage());
            return "http://no-coverart-available-at-source.se";
        } catch (TimeoutException e) {
            // IMPROVEMENT better solution
            LOGGER.info(e.getMessage());
            return "http://timeout.se";
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }

        return caData;
    }

}
