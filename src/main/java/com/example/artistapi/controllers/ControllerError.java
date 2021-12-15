package com.example.artistapi.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerError implements ErrorController{

    @GetMapping("/error")
    public String error() {
        return "{\"info\": \"Please provide an MBID in the url! For example: /070d193a-845c-479f-980e-bef15710653e\"}";
    }
}
