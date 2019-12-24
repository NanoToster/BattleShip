package ru.vsu.game_engine.players;

import ru.vsu.components.GameFieldButton;
import ru.vsu.domain.CellType;
import ru.vsu.utils.StaticGameInfoAccessor;

import java.util.Random;

import static ru.vsu.config.GameSettings.getGameSettings;
import static ru.vsu.utils.StaticGameInfoAccessor.getCellInPlayersGrid;

/**
 * @author Ivan Rovenskiy
 * 22 December 2019
 */
public class AiPlayer implements Player {
    @Override
    public CellType checkAndProcessShoot(GameFieldButton gameFieldButton) {
        if (CellType.getAllUnitsList().contains(gameFieldButton.getCellType())) {
            final CellType killedCellType = gameFieldButton.getCellType();
            gameFieldButton.setCellType(CellType.DeadShip);
            StaticGameInfoAccessor.appendGameLog("Ai killed our ship!!!!");
            return killedCellType;
        }
        if (CellType.Sea.equals(gameFieldButton.getCellType())
                || CellType.DeadZone.equals(gameFieldButton.getCellType())) {
            gameFieldButton.setCellType(CellType.Shooted);
            StaticGameInfoAccessor.appendGameLog("Ai - lox");
        }
        return null;
    }

    public static GameFieldButton aimForAShoot() {
        return randomShoot();
    }

    public static GameFieldButton randomShoot() {
        final int gameFieldSize = getGameSettings().getGameFieldSize();
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

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return new Random().nextInt((max - min) + 1) + min;
    }
}
