package com.techstack.game.exception;

import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler prepares error response based on the various 
 * exception handler class.
 * 
 * @author Karthikeyan N
 *
 */
@ControllerAdvice
public class GameExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(GameException.class)
	public final ResponseEntity<ExceptionResponse> handleGameException(final GameException e) {
		ExceptionResponse response = ExceptionResponse.of(e.getMessage(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(IllegalMoveException.class)
	public final ResponseEntity<ExceptionResponse> handleIllegalMove(final IllegalMoveException e) {
		ExceptionResponse response = ExceptionResponse.of(e.getMessage(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ExceptionResponse> handleValidation(final ConstraintViolationException e) {
		String errorMessage = new ArrayList<>(e.getConstraintViolations()).get(0).getMessage();
		ExceptionResponse response = ExceptionResponse.of(errorMessage, HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
}
