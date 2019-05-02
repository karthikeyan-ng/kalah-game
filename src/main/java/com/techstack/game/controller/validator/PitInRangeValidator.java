package com.techstack.game.controller.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class evaluates incoming REST path for {@code pitId} and determine that given pitId is
 * in valid range (1-14).
 * 
 * @author Karthikeyan N
 *
 */
public class PitInRangeValidator implements ConstraintValidator<PitInRange, Integer> {

	private int pitStartIndex;
    private int pitEndIndex;

    @Override
    public void initialize(PitInRange pitRange) {
        this.pitStartIndex = pitRange.min();
        this.pitEndIndex = pitRange.max();
    }

    @Override
    public boolean isValid(Integer pitId, ConstraintValidatorContext context) {
    	return pitId >= pitStartIndex && pitId <= pitEndIndex;
    }
}
