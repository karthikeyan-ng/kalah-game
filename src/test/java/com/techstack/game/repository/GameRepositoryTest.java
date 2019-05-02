package com.techstack.game.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.techstack.game.controller.api.Game;
import com.techstack.game.controller.api.GameResponseInfo;

/**
 * 
 * @author Karthikeyan N
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GameRepositoryTest {

	@Autowired
	private GameRepository gameRepository;

	@Test
	public void shouldCreateGame() {

		// given
		GameResponseInfo gameInfoResponse = gameRepository.create(6);

		// when
		Game game = gameRepository.findById(gameInfoResponse.getId());

		// assert
		Assert.assertNotNull(game);
		Assert.assertEquals(gameInfoResponse, gameInfoResponse);
	}
}
