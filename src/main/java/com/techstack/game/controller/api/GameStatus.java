package com.techstack.game.controller.api;

/**
 * Identifies the current game status
 *
 * @author Karthikeyan N
 */
public enum GameStatus {

	/**
	 * Game was initiated but not started.
	 */
	INIT,

	/**
	 * Player 1 is on the turn
	 */
	PLAYER1_TURN,

	/**
	 * Player 2 is on the turn
	 */
	PLAYER2_TURN,

	/**
	 * Game has finished
	 */
	FINISHED
}
