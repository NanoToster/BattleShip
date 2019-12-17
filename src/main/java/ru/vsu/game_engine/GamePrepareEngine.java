package ru.vsu.game_engine;

import ru.vsu.components.GameFieldButton;
import ru.vsu.components.GameFieldButton.FieldType;
import ru.vsu.domain.CellType;
import ru.vsu.exceptions.GameBaseException;
import ru.vsu.utils.StaticGameInfoAccessor;

import java.util.List;

import static ru.vsu.game_engine.StyleSearchEngine.processCellStyle;

/**
 * @author Ivan Rovenskiy
 * 18 December 2019
 */
public class GamePrepareEngine {
    // TODO maybe need to do interface GameEngine with processGameFieldButtonClick method
    //  and change instances when game staging changes
    private int shipsPreparedCount;
    private boolean isShipPreparing;
    // ship queue:
    // 4CellsShip ->
    // 3CellsShip * 2 ->
    // 2CellsShip * 3 ->
    // 1CellsShip * 4 ->
    // submarine * 1 ->
    // mine * 3 ->
    // minesweeper * 1

    public GamePrepareEngine() {
        this.shipsPreparedCount = 0;
        this.isShipPreparing = false;
    }

    public void processGameFieldButtonClick(GameFieldButton gameFieldButton) {
        if (gameFieldButton.getFieldType().equals(FieldType.Enemy)) {
            return;
        }

        final CellType requiredCellType = findRequiredCellType();
        if (List.of(CellType.CommonShip_4, CellType.CommonShip_3, CellType.CommonShip_2, CellType.CommonShip_1)
                .contains(requiredCellType)) {

            if (!this.isShipPreparing) {
                if (checkStartPlace(gameFieldButton.getRow(), gameFieldButton.getColumn())) {
                    this.isShipPreparing = true;

                    StaticGameInfoAccessor.appendGameLog("You start battleship placement");

                    if (gameFieldButton.getCellType().equals(CellType.Sea)) {
                        gameFieldButton.setCellType(requiredCellType);
                        gameFieldButton.getStyleClass().add(processCellStyle(gameFieldButton.getCellType()));
                    }
                }
            } else {
                if (checkEndPlaceValidation(gameFieldButton.getRow(), gameFieldButton.getColumn())) {
                    this.isShipPreparing = false;

                    StaticGameInfoAccessor.appendGameLog("You finish battleship placement");
                }
            }

            if (!this.isShipPreparing) {
                this.shipsPreparedCount++;
            }
        }
    }

    private boolean checkStartPlace(int row, int column) {
        // TODO add validation
        return true;
    }

    private boolean checkEndPlaceValidation(int row, int column) {
        // TODO add validation
        return true;
    }

    private CellType findRequiredCellType() {
        if (shipsPreparedCount == 0) {
            return CellType.CommonShip_4;
        } else if (shipsPreparedCount <= 3) {
            return CellType.CommonShip_3;
        } else if (shipsPreparedCount <= 6) {
            return CellType.CommonShip_2;
        } else if (shipsPreparedCount <= 10) {
            return CellType.CommonShip_1;
        } else if (shipsPreparedCount == 11) {
            return CellType.Submarine;
        } else if (shipsPreparedCount <= 14) {
            return CellType.Mine;
        } else if (shipsPreparedCount == 15) {
            return CellType.Minesweeper;
        } else {
            throw new GameBaseException.PrepareGameException("Unexpected ship number");
        }
    }
}
