package com.techstack.game.service;

import com.techstack.game.controller.api.GameResponseInfo;

/**
 * Kalah game consists of two operation.
 * 1. Initialize a game
 * 2. Play by pick stones from current players pit(1-6 and 8-13) except their Kalah/House(7 and 14)
 * 
 * @author Karthikeyan N
 *
 */
public interface IGame {

	GameResponseInfo initializeAGame(Integer initialStonesCountPerPit);

	GameResponseInfo play(String gameId, Integer pitId);

}
