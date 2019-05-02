package com.techstack.game.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techstack.game.controller.api.Board;
import com.techstack.game.controller.api.GameResponseInfo;
import com.techstack.game.controller.api.Pit;
import com.techstack.game.controller.validator.IsKalahPit;
import com.techstack.game.controller.validator.PitInRange;
import com.techstack.game.service.IGame;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Karthikeyan N
 *
 */
@Slf4j
@Api(value = GameController.GAME_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value = GameController.GAME_PATH, produces = { MediaType.APPLICATION_JSON_VALUE })
@RestController @Validated
public class GameController {

	public static final String GAME_PATH = "games";

	private final IGame gameService;

	public GameController(IGame gameService) {
		this.gameService = gameService;
	}

	/**
	 * Create a Kalah Game and Initialize the Stones in each players pit except player's Kalah/House
	 * 
	 * @return {@link GameResponseInfo}
	 */
	@ApiOperation(value = "Create a Kalah Game and Initialize the Stones in each players pit except player's Kalah/House", 
				  httpMethod = "POST", 
				  response = GameResponseInfo.class)
	@PostMapping
	public ResponseEntity<GameResponseInfo> createAGameAndFillDefaultStones() {

		log.info("initializing kalah game");

		return ResponseEntity.status(HttpStatus.CREATED).body(gameService.initializeAGame(Board.INITIAL_STONE_ON_PIT));
	}

	/**
	 * <p>Make a move based on the given GameId, PitId.</p> 
	 * <ul><li>Apply various game rules</li>
	 * <li>prepares current {@link Pit} and it's stones count</li></ul>
	 * 
	 * @param gameId current Game Identifier
	 * @param pitId selected Pit Identifier
	 * @return {@link GameResponseInfo}
	 */
	@ApiOperation(
			value = "Using gameId, pick stones from any pitId and make a move. Kalah game will let you know the status at the end of this move", 
			httpMethod = "PUT",
			response = GameResponseInfo.class)
	@PutMapping("/{gameId}/pits/{pitId}")
	public ResponseEntity<GameResponseInfo> makeAMove(
			@ApiParam(name = "gameId", value = "Current Game Identifier", required = true) 
			@PathVariable String gameId,
			
			@ApiParam(name = "pitId", value = "The selected PIT Identifier to make a move", required = true) 
			@PathVariable @PitInRange @IsKalahPit Integer pitId) {

		log.info("Picking stones from {} for the game {}", pitId, gameId);

		return ResponseEntity.ok().body(gameService.play(gameId, pitId));
	}
}
