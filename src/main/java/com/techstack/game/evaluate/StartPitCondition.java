package com.techstack.game.evaluate;

import com.techstack.game.controller.api.Board;
import com.techstack.game.controller.api.Game;
import com.techstack.game.controller.api.GameStatus;
import com.techstack.game.controller.api.Pit;
import com.techstack.game.controller.api.Player;
import com.techstack.game.exception.IllegalMoveException;

import lombok.extern.slf4j.Slf4j;

/**
 * This class check starting rules for the distributing stones.
 *
 * @author Karthikeyan N
 */
@Slf4j
public class StartPitCondition extends GameCondition {

	@Override
	public void apply(Game game, Pit startPit) {
		log.debug("check rules for the start pit {}", startPit);

		checkPlayerTurnRule(game, startPit);
		checkEmptyStartRule(startPit);
		this.next.apply(game, startPit);
	}

	private void checkPlayerTurnRule(Game game, Pit startPit) {

		if (game.getGameStatus().equals(GameStatus.INIT)) {
			GameStatus gameStatus = startPit.getPlayerId().equals(Player.PLAYER_1.getPlayerId()) ? GameStatus.PLAYER1_TURN
					: GameStatus.PLAYER2_TURN;
			game.setGameStatus(gameStatus);
		}

		if ((game.getGameStatus().equals(GameStatus.PLAYER1_TURN) && startPit.getPitId() >= Board.PLAYER1_KALAH)
				|| (game.getGameStatus().equals(GameStatus.PLAYER2_TURN)
						&& startPit.getPitId() <= Board.PLAYER1_KALAH)) {
			throw new IllegalMoveException("Incorrect pit to play");
		}
	}

	private void checkEmptyStartRule(Pit startPit) {

		if (startPit.getStoneCount() == 0) {
			throw new IllegalMoveException("Can not start from empty pit");
		}
	}
}
