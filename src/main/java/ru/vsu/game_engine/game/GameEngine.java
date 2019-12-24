package ru.vsu.game_engine.game;

import ru.vsu.components.GameFieldButton;

/**
 * @author Ivan Rovenskiy
 * 21 December 2019
 */
public interface GameEngine {
    void processGameFieldButtonClick(GameFieldButton gameFieldButton);

    GameStatus getGameStatus();

    enum GameStatus {
        Preparing,
        PreparingEnd,

        PlayersTurn,
        AITurn,

        PlayerWin,
        AiWin
    }
}
