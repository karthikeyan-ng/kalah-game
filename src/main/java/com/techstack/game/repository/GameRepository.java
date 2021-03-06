package com.techstack.game.repository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.techstack.game.controller.GameController;
import com.techstack.game.controller.api.Game;
import com.techstack.game.controller.api.GameResponseInfo;
import com.techstack.game.exception.GameException;

/**
 * This class is responsible for create and retrieve a Game by using In-Memory games store.
 * 
 * @author Karthikeyan N
 *
 */
@Repository
public class GameRepository {

	private static final Map<String, Game> games = new ConcurrentHashMap<>();

	/**
	 * This method create new Game and save the object in a Map.
	 *
	 * @param initialStonesCountPerPit
	 *            is the number of the stone of a pit.
	 * @return GameResponseInfo object.
	 */
	public GameResponseInfo create(Integer initialStonesCountPerPit) {
		String id = UUID.randomUUID().toString();
		Game game = new Game(initialStonesCountPerPit);
		game.setId(id);
		games.put(id, game);

		String gameLink = linkTo(methodOn(GameController.class).createAGameAndFillDefaultStones()).slash(id).toString();
		return GameResponseInfo.builder().id(id).url(gameLink).build();
	}

	/**
	 * This method will return the game object by id.
	 *
	 * @param id
	 *            is the game id.
	 * @return Game
	 */
	public Game findById(String id) {
		Game game = games.get(id);
		if (game == null) {
			throw new GameException("Game is not found for the id: " + id);
		}
		return game;
	}

}
