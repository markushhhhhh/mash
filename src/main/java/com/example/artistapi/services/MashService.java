package com.example.artistapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.example.artistapi.models.mash.Album;
import com.example.artistapi.models.mash.Artist;
import com.example.artistapi.models.mb.MbData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MashService {

    @Value("${wd.lang}")
    private String wdLang;

    @Autowired
    private MusicBrainzService musicBrainzService;

    @Autowired
    private WikiDataService wikiDataService;

    @Autowired
    private WikipediaService wikipediaService;

    @Autowired
    private CoverArtService coverArtService;

    private static Logger LOGGER = LoggerFactory.getLogger(MashService.class);

    public Artist mash(String mbid) {

        LOGGER.info("MASH starting for " + mbid);
        // Create Artist object where aggregated info shall be represented
        Artist theArtist = new Artist();
        theArtist.setMbid(mbid);

        // Get artist info & albums released from MB API
        LOGGER.info("Fetching MB-Data");
        MbData mbData = musicBrainzService.getMbData(mbid);

        // Start a thread fetching an albums(title(MB), id(MB), image(CAA)) list from
        // mb-data and CAA API
        LOGGER.info("Starting albums thread");
        CompletableFuture<List<Album>> albumsFuture = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Fetching Albums");
            return coverArtService.getAlbums(mbData);
        });

        // TODO - implement method that fetches wikipedia URL directly from mbData
        // getWikipediaDescDirectly();

        // Start a second thread fetching artist description from WP API, leveraging on
        // MB-data and the WD API
        LOGGER.info("Starting wiki thread");
        CompletableFuture<String> wikiFuture = CompletableFuture.supplyAsync(() -> {
            // Fetching title from WD API
            String title = wikiDataService.getTitle(mbData, "wikidata");
            if (title == null) {
                return "No wikipedia description ...";
            }
            // Fetching artist description from wikipedia
            String wpDesc = wikipediaService.getDescription(title);
            String trimmedWpDesc = wpDesc.replaceAll("\\n|(<p class=\"mw-empty-elt\">)", "").substring(4);
            return trimmedWpDesc;
        });

        // Combining the two threads and adding data to artist
        CompletableFuture<Artist> combinedInfo = albumsFuture
                .thenCombine(wikiFuture, (albums, wpDesc) -> {
                    theArtist.setAlbums(new ArrayList<Album>(albums));
                    theArtist.setDescription(wpDesc);
                    return theArtist;
                });

        try {
            // np = combinedInfo.get();
            return combinedInfo.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

}
