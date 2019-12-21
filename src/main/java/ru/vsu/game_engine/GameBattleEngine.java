package ru.vsu.game_engine;

import ru.vsu.components.GameFieldButton;
import ru.vsu.domain.CellType;
import ru.vsu.utils.StaticGameInfoAccessor;

import java.util.Random;

import static ru.vsu.config.CurrentGameSettings.getCurrentGameSettings;
import static ru.vsu.utils.StaticGameInfoAccessor.getCellInPlayersGrid;

/**
 * @author Ivan Rovenskiy
 * 21 December 2019
 */
public class GameBattleEngine implements GameEngine {
    private GameStatus currentStatus;

    public GameBattleEngine() {
        this.currentStatus = GameStatus.PlayersTurn;
    }

    @Override
    public void processGameFieldButtonClick(GameFieldButton gameFieldButton) {
        if (gameFieldButton.getFieldType().equals(GameFieldButton.FieldType.Player)) {
            StaticGameInfoAccessor.appendGameLog("You can't shoot yourself!");
            return;
        }
        checkAndProcessShootOfPlayer(gameFieldButton);
    }

    @Override
    public GameStatus getGameStatus() {
        return currentStatus;
    }

    private void checkAndProcessShootOfPlayer(GameFieldButton gameFieldButton) {
        if (CellType.Shooted.equals(gameFieldButton.getCellType())
                || CellType.DeadShip.equals(gameFieldButton.getCellType())) {
            StaticGameInfoAccessor.appendGameLog("Filter your actions! You are already shoot this cell");
            return;
        }
        if (CellType.getInvUnitsList().contains(gameFieldButton.getCellType())) {
            gameFieldButton.setCellType(CellType.DeadShip);
            StaticGameInfoAccessor.appendGameLog("You kill them all");
        }
        if (CellType.Sea.equals(gameFieldButton.getCellType())) {
            gameFieldButton.setCellType(CellType.Shooted);
            StaticGameInfoAccessor.appendGameLog("To the milk");
        }
        checkAndProcessShootOfAI(generateRandomAIShoot());
    }

    private GameFieldButton generateRandomAIShoot() {
        final int gameFieldSize = getCurrentGameSettings().getGameFieldSize();
        while (true) {
            int row = getRandomNumberInRange(1, gameFieldSize);
            int column = getRandomNumberInRange(1, gameFieldSize);
            StaticGameInfoAccessor.appendGameLog("AI trying");

            if (!getCellInPlayersGrid(row, column).getCellType().equals(CellType.Shooted)
                    && !getCellInPlayersGrid(row, column).getCellType().equals(CellType.DeadShip)) {
                return getCellInPlayersGrid(row, column);
            }
        }
    }

    private void checkAndProcessShootOfAI(GameFieldButton gameFieldButton) {
        if (CellType.getAllUnitsList().contains(gameFieldButton.getCellType())) {
            gameFieldButton.setCellType(CellType.DeadShip);
            StaticGameInfoAccessor.appendGameLog("Ai killed our ship!!!!");
            return;
        }
        if (CellType.Sea.equals(gameFieldButton.getCellType())
                || CellType.DeadZone.equals(gameFieldButton.getCellType())) {
            gameFieldButton.setCellType(CellType.Shooted);
            StaticGameInfoAccessor.appendGameLog("Ai - lox");
        }
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return new Random().nextInt((max - min) + 1) + min;
    }
}
