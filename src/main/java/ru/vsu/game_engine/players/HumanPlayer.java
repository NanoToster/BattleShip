package ru.vsu.game_engine.players;

import ru.vsu.components.GameFieldButton;
import ru.vsu.domain.CellType;
import ru.vsu.utils.StaticGameInfoAccessor;

/**
 * @author Ivan Rovenskiy
 * 22 December 2019
 */
public class HumanPlayer implements Player {
    @Override
    public CellType checkAndProcessShoot(GameFieldButton gameFieldButton) {
        final CellType killedCellType = gameFieldButton.getCellType();
        if (CellType.Shooted.equals(gameFieldButton.getCellType())
                || CellType.DeadShip.equals(gameFieldButton.getCellType())) {
            StaticGameInfoAccessor.appendGameLog("Filter your actions! You are already shoot this cell");
            return killedCellType;
        }
        if (CellType.getInvUnitsList().contains(gameFieldButton.getCellType())) {
            gameFieldButton.setCellType(CellType.DeadShip);
            StaticGameInfoAccessor.appendGameLog("You kill them all");
            return killedCellType;
        }
        if (CellType.Sea.equals(gameFieldButton.getCellType())) {
            gameFieldButton.setCellType(CellType.Shooted);
            StaticGameInfoAccessor.appendGameLog("To the milk");
            return null;
        }
        return null;
    }
}
