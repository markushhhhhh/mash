package com.example.artistapi.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.artistapi.apis.CoverArtApi;
import com.example.artistapi.exceptions.NoCoverArtException;
import com.example.artistapi.models.mash.Album;
import com.example.artistapi.models.mb.MbData;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.netty.handler.timeout.TimeoutException;

@Service
public class CoverArtService {

    @Autowired
    CoverArtApi caApi;

    private static Logger LOGGER = LoggerFactory.getLogger(CoverArtService.class);

    public List<Album> getAlbums(MbData mbData) {

        List<Album> albumsList = mbData.getReleaseGroups().stream().parallel()
                .map((releaseGroup) -> {
                    Album alb = new Album();
                    // set id and title
                    alb.setId(releaseGroup.getId());
                    alb.setTitle(releaseGroup.getTitle());
                    try {
                        // Calling CAA to get image URL
                        alb.setImage(new URL(getCoverArtForAlbum(releaseGroup.getId())));
                    } catch (MalformedURLException e) {
                        LOGGER.info(e.getMessage());
                        alb.setImage(null);
                    }
                    return alb;
                }).collect(Collectors.toList());
        LOGGER.info("Albums added");
        return albumsList;
    }

    private String getCoverArtForAlbum(String id) {
        String coverArtResponse;
        try {
            coverArtResponse = caApi.callCoverArt(id);
        } catch (NoCoverArtException e) {
            // IMPROVEMENT might move to API client to improve caching
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
        return getUrlFromCaResponse(coverArtResponse);
    }

    private String getUrlFromCaResponse(String coverArtResponse) {
        List<String> caUrls = new ArrayList<String>();
        if (coverArtResponse == null) {
            return null;
        }

        try {
            caUrls = JsonPath.parse(coverArtResponse).read("$.images[*].image");
        } catch (PathNotFoundException e) {
            LOGGER.info(e.getMessage());
            return null;
        }

        if (caUrls.size() > 0) {
            return caUrls.get(0);
        }
        return null;
    }

}
