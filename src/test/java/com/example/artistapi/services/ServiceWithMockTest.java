package com.example.artistapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.artistapi.apis.CoverArtApi;
import com.example.artistapi.apis.MusicBrainzApi;
import com.example.artistapi.apis.WikiDataApi;
import com.example.artistapi.apis.WikipediaApi;
import com.example.artistapi.exceptions.NoCoverArtException;
import com.example.artistapi.models.mash.Album;
import com.example.artistapi.models.mb.EntityUrl;
import com.example.artistapi.models.mb.MbData;
import com.example.artistapi.models.mb.Relation;
import com.example.artistapi.models.mb.ReleaseGroup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import io.netty.handler.timeout.TimeoutException;

@SpringBootTest
public class ServiceWithMockTest {


    @MockBean
    private MusicBrainzApi musicBrainzApi;

    @MockBean
    private WikiDataApi wikiDataApi;

    @MockBean
    private WikipediaApi wikipediaApi;

    @MockBean
    private CoverArtApi coverArtApi;

    @Autowired
    private MusicBrainzService musicBrainzService;

    @Autowired
    private WikiDataService wikiDataService;

    @Autowired
    private WikipediaService wikipediaService;

    @Autowired
    private CoverArtService coverArtService;

    @Test
    void testMusicBrainsServiceHappyFlow() {
        String mbid = "5b11f4ce-a62d-471e-81fc-a69a8278c7da";
        String expectedName = "name";
        MbData mockedMbResponse = createMockedResponsefromMb("name", "type", "http://test.se", "albumId", "albumTitle");
        when(musicBrainzApi.callMusicbrainz(mbid)).thenReturn(mockedMbResponse);
        MbData mbData = musicBrainzService.getMbData(mbid);
        assertEquals(expectedName, mbData.getName());
        assertEquals(1, mbData.getReleaseGroups().size());
        assertEquals(1, mbData.getRelations().size());
        verify(musicBrainzApi).callMusicbrainz(mbid);
    }

    @Test
    void testCoverArtServiceGetAlbumsHappyFlow() throws TimeoutException, NoCoverArtException, Exception {
        Album expectedAlb;
        expectedAlb = new Album("albumTitle", "albumId", new URL("http://coverart.se"));
        String mockedCaResponse = "{\"images\":[{\"approved\":true,\"back\":false,\"comment\":\"\",\"edit\":20473306,\"front\":true,\"id\":3012495605,\"image\":\"http://coverart.se\",\"thumbnails\":{\"250\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-250.jpg\",\"500\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-500.jpg\",\"1200\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-1200.jpg\",\"large\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-500.jpg\",\"small\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-250.jpg\"},\"types\":[\"Front\"]},{\"approved\":true,\"back\":false,\"comment\":\"\",\"edit\":71237777,\"front\":false,\"id\":26670836881,\"image\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881.jpg\",\"thumbnails\":{\"250\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-250.jpg\",\"500\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-500.jpg\",\"1200\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-1200.jpg\",\"large\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-500.jpg\",\"small\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-250.jpg\"},\"types\":[\"Front\"]}],\"release\":\"https://musicbrainz.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d\"}";
        when(coverArtApi.callCoverArt("albumId")).thenReturn(mockedCaResponse);
        MbData mockedMbResponse = createMockedResponsefromMb("name", "type", "http://test.se", "albumId", "albumTitle");
        List<Album> albums = coverArtService.getAlbums(mockedMbResponse);
        assertEquals(expectedAlb.getImage(), albums.get(0).getImage());
        verify(coverArtApi).callCoverArt("albumId");
    }

    @Test
    void testCoverArtServiceGetAlbumsUnhappyFlow2NullValueAtImages()
            throws TimeoutException, NoCoverArtException, Exception {
        Album expectedAlb;
        expectedAlb = new Album("albumTitle", "albumId", null);
        String mockedCaResponse = "{\"images\":[{\"approved\":true,\"back\":false,\"comment\":\"\",\"edit\":20473306,\"front\":true,\"id\":3012495605,\"image\":null,\"thumbnails\":{\"250\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-250.jpg\",\"500\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-500.jpg\",\"1200\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-1200.jpg\",\"large\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-500.jpg\",\"small\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-250.jpg\"},\"types\":[\"Front\"]},{\"approved\":true,\"back\":false,\"comment\":\"\",\"edit\":71237777,\"front\":false,\"id\":26670836881,\"image\":null,\"thumbnails\":{\"250\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-250.jpg\",\"500\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-500.jpg\",\"1200\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-1200.jpg\",\"large\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-500.jpg\",\"small\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-250.jpg\"},\"types\":[\"Front\"]}],\"release\":\"https://musicbrainz.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d\"}";
        when(coverArtApi.callCoverArt("albumId")).thenReturn(mockedCaResponse);
        MbData mockedMbResponse = createMockedResponsefromMb("name", "type", "http://test.se", "albumId", "albumTitle");
        List<Album> albums = coverArtService.getAlbums(mockedMbResponse);
        assertEquals(expectedAlb.getImage(), albums.get(0).getImage());
        verify(coverArtApi).callCoverArt("albumId");
    }

    @Test
    void testCoverArtServiceGetAlbumsUnhappyFlow3IncorrectUrlFormat()
            throws TimeoutException, NoCoverArtException, Exception {
        Album expectedAlb = new Album("albumTitle", "albumId", null);
        String mockedCaResponse = "{\"images\":[{\"approved\":true,\"back\":false,\"comment\":\"\",\"edit\":20473306,\"front\":true,\"id\":3012495605,\"image\":\"incorrecturl\",\"thumbnails\":{\"250\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-250.jpg\",\"500\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-500.jpg\",\"1200\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-1200.jpg\",\"large\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-500.jpg\",\"small\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/3012495605-250.jpg\"},\"types\":[\"Front\"]},{\"approved\":true,\"back\":false,\"comment\":\"\",\"edit\":71237777,\"front\":false,\"id\":26670836881,\"image\":\"incorrecturl\",\"thumbnails\":{\"250\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-250.jpg\",\"500\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-500.jpg\",\"1200\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-1200.jpg\",\"large\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-500.jpg\",\"small\":\"http://coverartarchive.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d/26670836881-250.jpg\"},\"types\":[\"Front\"]}],\"release\":\"https://musicbrainz.org/release/a146429a-cedc-3ab0-9e41-1aaf5f6cdc2d\"}";
        when(coverArtApi.callCoverArt("albumId")).thenReturn(mockedCaResponse);
        MbData mockedMbResponse = createMockedResponsefromMb("name", "type", "http://test.se", "albumId", "albumTitle");
        List<Album> albums = coverArtService.getAlbums(mockedMbResponse);
        assertEquals(expectedAlb.getImage(), albums.get(0).getImage());
        verify(coverArtApi).callCoverArt("albumId");
    }

    @Test
    void testCoverArtServiceGetAlbumsUnhappyFlow4IncorrectJsonFormat()
            throws TimeoutException, NoCoverArtException, Exception {
        Album expectedAlb = new Album("albumTitle", "albumId", null);
        String mockedCaResponse = "{}";
        when(coverArtApi.callCoverArt("albumId")).thenReturn(mockedCaResponse);
        MbData mockedMbData = createMockedResponsefromMb("name", "type", "http://test.se", "albumId", "albumTitle");
        List<Album> albums = coverArtService.getAlbums(mockedMbData);
        assertEquals(expectedAlb.getImage(), albums.get(0).getImage());
        verify(coverArtApi).callCoverArt("albumId");
    }

    @Test
    void testWikiDataServiceGetTitleHappyFlow() throws JsonMappingException, JsonProcessingException {
        String entity = "Q11649";
        String responseString = "{\"entities\":{\"Q11649\":{\"type\":\"item\",\"id\":\"Q11649\",\"sitelinks\":{\"enwiki\":{\"site\":\"enwiki\",\"title\":\"Nirvana (band)\",\"badges\":[\"Q17437796\"]},\"enwikiquote\":{\"site\":\"enwikiquote\",\"title\":\"Nirvana (band)\",\"badges\":[]},\"eowiki\":{\"site\":\"eowiki\",\"title\":\"Nirvana\",\"badges\":[]},\"eswiki\":{\"site\":\"eswiki\",\"title\":\"Nirvana (banda)\",\"badges\":[\"Q17437796\"]}}}},\"success\":1}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode mockedResponse = mapper.readTree(responseString);
        when(wikiDataApi.callWikiData(entity)).thenReturn(mockedResponse);
        MbData mockedMbData = createMockedResponsefromMb("name", "wikidata", "https://www.wikidata.org/wiki/Q11649",
                "albumId", "albumTitle");

        String title = wikiDataService.getTitle(mockedMbData, "wikidata");
        assertEquals("Nirvana (band)", title);
        verify(wikiDataApi).callWikiData(entity);
    }

    @Test
    void testWikiDataServiceGetTitleUnhappyFlowNullValue() throws JsonMappingException, JsonProcessingException {
        String entity = "Q11649";
        when(wikiDataApi.callWikiData(entity)).thenReturn(null);
        MbData mockedMbData = createMockedResponsefromMb("name", "wikidata", "https://www.wikidata.org/wiki/Q11649",
                "albumId", "albumTitle");

        String title = wikiDataService.getTitle(mockedMbData, "wikidata");
        assertEquals(null, title);
    }

    @Test
    void testWikiDataServiceGetTitleUnhappyFlowNoWikidataLink() throws JsonMappingException, JsonProcessingException {
        String entity = "Q11649";
        String responseString = "{\"entities\":{\"Q11649\":{\"type\":\"item\",\"id\":\"Q11649\",\"sitelinks\":{\"enwiki\":{\"site\":\"enwiki\",\"title\":\"Nirvana (band)\",\"badges\":[\"Q17437796\"]},\"enwikiquote\":{\"site\":\"enwikiquote\",\"title\":\"Nirvana (band)\",\"badges\":[]},\"eowiki\":{\"site\":\"eowiki\",\"title\":\"Nirvana\",\"badges\":[]},\"eswiki\":{\"site\":\"eswiki\",\"title\":\"Nirvana (banda)\",\"badges\":[\"Q17437796\"]}}}},\"success\":1}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode mockedResponse = mapper.readTree(responseString);
        when(wikiDataApi.callWikiData(entity)).thenReturn(mockedResponse);
        MbData mockedMbData = createMockedResponsefromMb("name", "other", "https://www.wikidata.org/wiki/Q11649",
                "albumId", "albumTitle");

        String title = wikiDataService.getTitle(mockedMbData, "wikidata");
        assertEquals(null, title);
        verifyNoInteractions(wikiDataApi);
    }

    @Test
    void testWikipediaServiceGetDescriptionHappyFlow() throws JsonMappingException, JsonProcessingException {
        String title = "Nirvana (band)";
        String expectedDesc = "description...";
        String responseString = "{\"batchcomplete\":\"\",\"warnings\":{\"extracts\":{\"*\":\"HTML may be malformed.\"}},\"query\":{\"normalized\":[{\"from\":\"Nirvana_(band)\",\"to\":\"Nirvana (band)\"}],\"pages\":{\"21231\":{\"pageid\":21231,\"ns\":0,\"title\":\"Nirvana (band)\",\"extract\":\"description...\"}}}}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode mockedResponse = mapper.readTree(responseString);
        when(wikipediaApi.callWikipedia(title)).thenReturn(mockedResponse);
        String desc = wikipediaService.getDescription(title);
        assertEquals(expectedDesc, desc);
        verify(wikipediaApi).callWikipedia(title);
    }

    @Test
    void testWikipediaServiceGetDescriptionUnhappyFlow() throws JsonMappingException, JsonProcessingException {
        String title = "Nirvana (band)";
        String expectedDesc = null;
        when(wikipediaApi.callWikipedia(title)).thenReturn(null);
        String desc = wikipediaService.getDescription(title);
        assertEquals(expectedDesc, desc);
        verify(wikipediaApi).callWikipedia(title);
    }

    private MbData createMockedResponsefromMb(String name, String type, String url, String albumId, String albumTitle) {
        MbData mbDataExpected = new MbData();
        mbDataExpected.setName(name);
        ArrayList<Relation> relationsList = new ArrayList<Relation>();
        Relation relExpected = new Relation();
        relExpected.setType(type);
        EntityUrl entUrlExpected = new EntityUrl();
        URL urlExpected;
        try {
            urlExpected = new URL(url);
            entUrlExpected.setResource(urlExpected);
        } catch (MalformedURLException e) {
            entUrlExpected.setResource(null);
        }
        relExpected.setUrl(entUrlExpected);
        relationsList.add(relExpected);
        mbDataExpected.setRelations(relationsList);
        ArrayList<ReleaseGroup> releaseList = new ArrayList<ReleaseGroup>();
        ReleaseGroup releaseGroup = new ReleaseGroup();
        releaseGroup.setId(albumId);
        releaseGroup.setTitle(albumTitle);
        releaseList.add(releaseGroup);
        mbDataExpected.setReleaseGroups(releaseList);
        return mbDataExpected;
    }

}
