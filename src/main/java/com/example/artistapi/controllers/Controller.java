package com.example.artistapi.controllers;

import com.example.artistapi.exceptions.NoArtistException;
import com.example.artistapi.models.mash.Artist;
import com.example.artistapi.services.MashService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    MashService mashService;

    @GetMapping("/{mbid}")
    public Artist getArtist(@PathVariable("mbid") String mbid) {

        try {
            return mashService.mash(mbid);
        } catch (NoArtistException exc) {
            // IMPROVEMENT - Might want to return an error object instead of Artist
            Artist art = new Artist();
            art.setMbid(mbid);
            art.setDescription(exc.getMessage());
            return art;
        }
    }

}
