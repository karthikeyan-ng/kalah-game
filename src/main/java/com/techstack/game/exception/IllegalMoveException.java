package com.techstack.game.exception;

/**
 * This exception will be thrown whenever system behaves differently.
 * 
 * @author Karthikeyan N
 *
 */
public class IllegalMoveException extends RuntimeException {

	private static final long serialVersionUID = 869826560650856941L;

	public IllegalMoveException(String message) {
		super(message);
	}
}
