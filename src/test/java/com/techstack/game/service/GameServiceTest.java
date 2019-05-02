package com.techstack.game.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.techstack.game.controller.GameController;
import com.techstack.game.controller.api.Board;
import com.techstack.game.controller.api.Game;
import com.techstack.game.controller.api.GameResponseInfo;
import com.techstack.game.controller.api.GameStatus;
import com.techstack.game.controller.api.Pit;
import com.techstack.game.controller.api.Player;
import com.techstack.game.evaluate.EvaluateGameFlow;
import com.techstack.game.repository.GameRepository;

/**
 * 
 * @author Karthikeyan N
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private EvaluateGameFlow gameEngine;

    @Autowired
    private IGame gameService;

    @Test
    public void shouldInitGame(){
        
        GameResponseInfo gameResponse = new GameResponseInfo();
        gameResponse.setId(UUID.randomUUID().toString());
        gameResponse.setUrl(linkTo(methodOn(GameController.class).createAGameAndFillDefaultStones()).slash(gameResponse.getId())
				.toString());
        gameResponse.setStatus(null);

        //given
        BDDMockito.given(gameRepository.create(BDDMockito.any())).willReturn(gameResponse);

        //when
        GameResponseInfo mockGame = gameService.initializeAGame(6);

        //then
        Assert.assertEquals(gameResponse, mockGame);

    }

    @Test
    public void shouldPlayGame(){

        Player player1 = Player.PLAYER_1;
        Player player2 = Player.PLAYER_2;

        Board board = new Board();
        board.setPits(initPit());

        String id = UUID.randomUUID().toString();
        Game game = new Game(Board.INITIAL_STONE_ON_PIT);
        game.setGameStatus(GameStatus.INIT);
        game.setId(id);
        game.setPlayer1(player1);
        game.setPlayer2(player2);
        game.setBoard(board);

        //given1
        BDDMockito.given(gameRepository.findById(id)).willReturn(game);

        //given2
        Map<String, String> gameStatusCurrentValues = new TreeMap<>();
        gameStatusCurrentValues.put("1", "6");
        
        GameResponseInfo gameResponse = new GameResponseInfo();
        gameResponse.setId(UUID.randomUUID().toString());
        gameResponse.setUrl(linkTo(methodOn(GameController.class).createAGameAndFillDefaultStones()).slash(gameResponse.getId())
				.toString());
        gameResponse.setStatus(gameStatusCurrentValues);
        
        BDDMockito.given(gameRepository.create(BDDMockito.any())).willReturn(gameResponse);

        //when
        GameResponseInfo mockResponse =  gameService.play(game.getId(), game.getBoard().getPits().get(1).getPitId());

        //then
        Assert.assertEquals(gameResponse.getStatus().isEmpty(), mockResponse.getStatus().isEmpty());
    }

    private Map<Integer, Pit> initPit(){
        Map<Integer, Pit> pits = new HashMap<>();
        
        pits.putAll(createPits(Board.PIT_START_ID, Board.PLAYER1_KALAH, Board.INITIAL_STONE_ON_PIT, Player.PLAYER_1.getPlayerId()));
        
        Pit house1 = new Pit(Board.PLAYER1_KALAH, Board.INITIAL_STONE_ON_KALAH, Player.PLAYER_1.getPlayerId());
        pits.put(Board.PLAYER1_KALAH, house1);

        pits.putAll(createPits(Board.PLAYER1_KALAH + 1, Board.PLAYER2_KALAH, Board.INITIAL_STONE_ON_PIT, Player.PLAYER_2.getPlayerId()));
        
        Pit house2 = new Pit(Board.PLAYER2_KALAH, Board.INITIAL_STONE_ON_KALAH, Player.PLAYER_2.getPlayerId());
        pits.put(Board.PLAYER2_KALAH, house2);

        return pits;
    }

    private Map<Integer, Pit> createPits(Integer pitStartId, Integer pitEndId, Integer initialStoneOnPit, Integer playerId) {
    	return 
	    	IntStream.range(pitStartId, pitEndId)
			 	.mapToObj(pitId -> new Pit(pitId, initialStoneOnPit, playerId))
			 	.collect(Collectors.toMap(Pit::getPitId, Function.identity()));
    }
}
