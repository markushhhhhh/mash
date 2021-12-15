package com.example.artistapi.services;

import com.example.artistapi.apis.MusicBrainzApi;
import com.example.artistapi.models.mb.MbData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicBrainzService {

    @Autowired
    MusicBrainzApi mbApi;


    public MbData getMbData(String mbid) {
        return mbApi.callMusicbrainz(mbid);
    }

}
