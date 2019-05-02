package com.techstack.game.controller.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class evaluates incoming REST path for {@code pitId} and determine that player is
 * trying to move their own Kalah/House stones. 
 * 
 * @author Karthikeyan N
 *
 */
public class IsKalahHousePitValidator implements ConstraintValidator<IsKalahPit, Integer> {

	private int player1KalahPit;
    private int player2KalahPit;

    @Override
    public void initialize(IsKalahPit kalahPit) {
        this.player1KalahPit = kalahPit.player1Kalah();
        this.player2KalahPit = kalahPit.player2Kalah();
    }

    @Override
    public boolean isValid(Integer pitId, ConstraintValidatorContext context) {
    	return !(pitId.equals(player1KalahPit) || pitId.equals(player2KalahPit));
    }
}
