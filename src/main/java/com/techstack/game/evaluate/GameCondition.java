package com.techstack.game.evaluate;

import com.techstack.game.controller.api.Game;
import com.techstack.game.controller.api.Pit;

public abstract class GameCondition {

	protected GameCondition next;

	public abstract void apply(Game game, Pit currentPit);

	public GameCondition setNext(GameCondition next) {
		this.next = next;
		return next;
	}
	
}
