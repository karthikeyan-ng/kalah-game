package com.techstack.game.evaluate;

import com.techstack.game.controller.api.Game;
import com.techstack.game.controller.api.Pit;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is responsible to distribute to the next pits except for the
 * opponent house.
 *
 * @author Karthikeyan N
 */
@Slf4j
public class DistributePitStoneRule extends GameCondition {

	@Override
	public void apply(Game game, Pit currentPit) {
		log.debug("check the rules for distributing stone to the next pit(s)");

		Integer stoneToDistribute = currentPit.getStoneCount();
		currentPit.setStoneCount(0);

		for (int i = 0; i < stoneToDistribute; i++) {
			currentPit = game.getBoard().getNextPit(currentPit);
			log.debug("next pit {}", currentPit);
			if (currentPit.isDistributable(game.getGameStatus())) {
				currentPit.setStoneCount(currentPit.getStoneCount() + 1);
			} else {
				i--;
			}
		}
		
		this.next.apply(game, currentPit);
	}

}
