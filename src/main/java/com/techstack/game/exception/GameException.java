package com.techstack.game.exception;

/**
 * This exception will be thrown whenever system behaves differently.
 * 
 * @author Karthikeyan N
 *
 */
public class GameException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GameException(String message) {
		super(message);
	}
}
