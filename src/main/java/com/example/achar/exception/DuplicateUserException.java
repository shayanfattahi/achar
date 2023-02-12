package com.example.achar.exception;

public class DuplicateUserException extends Error {

	public DuplicateUserException(String name) {
		super("The user {name} is a duplicate.".replace("{name}", name));
	}

	public DuplicateUserException() {
		this("Anonymous");
	}
}
