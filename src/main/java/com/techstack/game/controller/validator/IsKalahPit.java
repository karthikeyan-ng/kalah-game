package com.techstack.game.controller.validator;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * This class evaluates incoming REST path for {@code pitId} and determine that player is
 * trying to move their own Kalah/House stones.
 * 
 * @author Karthikeyan N
 *
 */
@Target({PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = IsKalahHousePitValidator.class)
public @interface IsKalahPit {

	String message() default "Kalah or House stone(s) are not allowed to distribute.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int player1Kalah() default 7;

    int player2Kalah() default 14;
}
