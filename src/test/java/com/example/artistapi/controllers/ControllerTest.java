package com.example.artistapi.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.util.ArrayList;

import com.example.artistapi.models.mash.Album;
import com.example.artistapi.models.mash.Artist;
import com.example.artistapi.services.MashService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MashService mashupApiService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Tests get artist api")
    public void getArtist() throws Exception {

        Album album = new Album();
        album.setTitle("title");
        album.setId("5678");
        album.setImage(new URL("http", "coverartarchive.org", "release/thefile"));
        ArrayList<Album> albums = new ArrayList<Album>();
        albums.add(album);

        Artist artist = new Artist();
        artist.setMbid("1234");
        artist.setDescription("description bla bla");
        artist.setAlbums(albums);

        when(mashupApiService.mash("123")).thenReturn(artist);

        Artist artist2 = new Artist();
        artist2.setMbid("1234");
        artist2.setDescription("description bla bla");
        artist2.setAlbums(albums);

        final String expectedResponseContent = objectMapper.writeValueAsString(artist2);

        this.mockMvc.perform(get("/123")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseContent));
        // .andExpect(jsonPath("$.mbid").value("from the real"));
        verify(mashupApiService).mash("123");
    }
}
