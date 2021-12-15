package com.example.artistapi.apis;

import java.util.function.Predicate;

import com.example.artistapi.exceptions.NoArtistException;
import com.example.artistapi.models.mb.MbData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class MusicBrainzApi {

    private static Logger LOGGER = LoggerFactory.getLogger(MusicBrainzApi.class);

    @Value("${mb.endpoint}")
    private String mbEndpoint;

    @Cacheable("musicbrainz")
    public MbData callMusicbrainz(String mbid) {

        LOGGER.info("Calling MB API");
        WebClient wc = WebClient.create();
        MbData mbData = wc.get()
                .uri(mbEndpoint, mbid)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(status -> status.equals(HttpStatus.BAD_REQUEST),
                        response -> Mono.error(new NoArtistException("No artist found for MBID: " + mbid
                                + ". Try with '5b11f4ce-a62d-471e-81fc-a69a8278c7da' or '070d193a-845c-479f-980e-bef15710653e' instead :)")))
                .bodyToMono(MbData.class)
                .onErrorMap(Predicate.not(NoArtistException.class::isInstance), throwable -> {
                    return new NoArtistException("Sorry - Error calling underlying api (MusicBrainz)");
                })
                .block();

        LOGGER.info("MB-data retrieved for artist: " + mbData.getName());
        return mbData;

    }
}
