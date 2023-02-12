package com.example.achar.exception;

public class InvalidEntityException extends Error {

	public InvalidEntityException(String name) {
		super("The user {name} is an invalid user.".replace("{name}", name));
	}

	public InvalidEntityException() {
		this("Anonymous");
	}
}
