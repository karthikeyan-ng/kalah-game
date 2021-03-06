/**
 * 
 */
package com.techstack.game.controller.api;

/**
 * This Kalah game is supported for TWO players.
 * 
 * @author Karthikeyan N
 *
 */
public enum Player {

	PLAYER_1 (1),
	PLAYER_2 (2);
	
	private Integer playerId;
	
	private Player(Integer playerId) {
		this.playerId = playerId;
	}

	public Integer getPlayerId() {
		return playerId;
	}

}
