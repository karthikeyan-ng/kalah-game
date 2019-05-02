package com.techstack.game.evaluate;

import org.springframework.stereotype.Component;

import com.techstack.game.controller.api.Game;
import com.techstack.game.controller.api.Pit;

/**
 * This class used to prepare Game evaluation conditions. Later based on Game play condition
 * will be evaluated in a sequential order.
 *
 * @author Karthikeyan N
 */
@Component
public class EvaluateGameFlow {

	private final GameCondition condition;

	/**
	 * Evaluate the current game based on predefined set of condition order.
	 */
	public EvaluateGameFlow() {

		this.condition = new StartPitCondition();
		condition.setNext(new DistributePitStoneRule())
				 .setNext(new EndPitCondition())
				 .setNext(new GameOver());
	}

	public void execute(Game game, Pit pit) {
		this.condition.apply(game, pit);
	}

}
