/**
 * 
 */
package com.techstack.game.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.techstack.game.controller.GameController;
import com.techstack.game.controller.api.Game;
import com.techstack.game.controller.api.GameResponseInfo;
import com.techstack.game.controller.api.Pit;
import com.techstack.game.evaluate.EvaluateGameFlow;
import com.techstack.game.repository.GameRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Kalah game consists of two operation.
 * 1. Initialize a game
 * 2. Play by pick stones from current players pit(1-6 and 8-13) except their Kalah/House(7 and 14)
 * 
 * @author Karthikeyan N
 *
 */
@Service @Slf4j
public class GameService implements IGame {

	private final GameRepository gameRepository;
	private final EvaluateGameFlow gameEngine;

	public GameService(GameRepository gameRepository, EvaluateGameFlow gameEngine) {
		this.gameRepository = gameRepository;
		this.gameEngine = gameEngine;
	}

	/**
	 * This method is responsible to initialize new game
	 *
	 * @param initialStonesCountPerPit
	 *            is the initial number of stone.
	 * @return GameInfo
	 */
	@Override
	public GameResponseInfo initializeAGame(Integer initialStonesCountPerPit) {
		return gameRepository.create(initialStonesCountPerPit);
	}

	/**
	 * This method is responsible for every new move of the stones from a pit.
	 *
	 * @param gameId
	 *            game id
	 * @param pitId
	 *            id of the pit
	 * @return GameResponseInfo
	 */
	@Override
	public GameResponseInfo play(String gameId, Integer pitId) {
		log.debug("gameId {}, pitId {}", gameId, pitId);

		Game game = gameRepository.findById(gameId);
		Pit pit = game.getBoard().getPitByPitId(pitId);

		gameEngine.execute(game, pit);

		return createCurrentGameStatusResponse(gameId, game);

	}

	private GameResponseInfo createCurrentGameStatusResponse(String gameId, Game game) {

		Map<String, String> statusInfo = new TreeMap<>();

		game.getBoard().getPits().entrySet().forEach(entry -> {
			statusInfo.put(Integer.toString(entry.getKey()), Integer.toString(entry.getValue().getStoneCount()));
		});

		String gameLink = linkTo(methodOn(GameController.class).createAGameAndFillDefaultStones()).slash(gameId)
				.toString();

		return GameResponseInfo.builder().id(gameId).url(gameLink).status(statusInfo).build();
	}

}
