package com.example.artistapi.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="No such artist")
public class NoArtistException extends RuntimeException {

	public NoArtistException(String message) {
		super(message);
	}
}
