package ru.vsu.game_engine;

import ru.vsu.components.GameFieldButton;
import ru.vsu.components.GameFieldButton.FieldType;
import ru.vsu.config.CurrentGameSettings;
import ru.vsu.domain.CellType;
import ru.vsu.utils.StaticGameInfoAccessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Rovenskiy
 * 18 December 2019
 */
public class GamePrepareEngine {
    // TODO maybe need to do interface GameEngine with processGameFieldButtonClick method
    //  and change instances when game staging changes

    private CurrentGameSettings currentGameSettings;
    private PreparingShip preparingShip;

    public GamePrepareEngine() {
        this.preparingShip = null;
        this.currentGameSettings = CurrentGameSettings.getCurrentGameSettings();
    }

    public void processGameFieldButtonClick(GameFieldButton gameFieldButton) {
        if (gameFieldButton.getFieldType().equals(FieldType.Enemy)) {
            return;
        }

        if (this.preparingShip == null) {
            final CellType nextShipInQueueType = currentGameSettings.getShipPlacementQueue().peek();
            if (nextShipInQueueType == null) {
                StaticGameInfoAccessor.appendGameLog("Prepare game ends");
                return;
            }
            if (!checkStartPlace(gameFieldButton.getRow(), gameFieldButton.getColumn(), nextShipInQueueType)) {
                StaticGameInfoAccessor.appendGameLog("Invalid cell to place " + nextShipInQueueType);
                return;
            }

            StaticGameInfoAccessor.appendGameLog("You start " + nextShipInQueueType + " placement");
            currentGameSettings.getShipPlacementQueue().remove(nextShipInQueueType);
            if (nextShipInQueueType.getRange() > 1) {
                gameFieldButton.setCellType(CellType.Selected);
                this.preparingShip = new PreparingShip(gameFieldButton.getColumn(), gameFieldButton.getRow(), nextShipInQueueType);
            }

            if (nextShipInQueueType.getRange() == 1) {
                killCellsAroundCell(new CellPoint(gameFieldButton.getColumn(), gameFieldButton.getRow()));
                gameFieldButton.setCellType(nextShipInQueueType);
            }
        } else {
            if (endPlacement(gameFieldButton.getRow(), gameFieldButton.getColumn())) {

                StaticGameInfoAccessor.appendGameLog("You finish battleship placement");
                this.preparingShip = null;
            }
        }
    }

    private boolean checkStartPlace(int row, int column, CellType requiredCellType) {
        final GameFieldButton gameFieldButton = StaticGameInfoAccessor.getCellInPlayersGrid(row, column);
        if (gameFieldButton == null) {
            return false;
        }
        if (gameFieldButton.getCellType().equals(CellType.Sea)) {
            return true;
        }
        if (gameFieldButton.getCellType().equals(CellType.DeadZone) && requiredCellType.equals(CellType.Submarine)) {
            return true;
        }
        return false;
    }

    private boolean endPlacement(int row, int column) {
        if (preparingShip.getRow() == row) {
            return processHorizontalPlacement(column);
        }
        if (preparingShip.getColumn() == column) {
            return processVerticalPlacement(row);
        }

        StaticGameInfoAccessor.appendGameLog("Wrong place for ship placement finish");
        return false;
    }

    private boolean processVerticalPlacement(int vectorRow) {
        int startRow = preparingShip.getRow();
        int column = preparingShip.getColumn();
        List<CellPoint> cellPointList = new ArrayList<>();

        int inc = vectorRow > startRow ? 1 : -1;
        for (int range = 0; Math.abs(range) < preparingShip.cellType.getRange(); range = range + inc) {
            if (startRow + range < 1 || startRow + range > currentGameSettings.getGameFieldSize()) {
                StaticGameInfoAccessor.appendGameLog("To close to game field border");
                return false;
            }
            if (List.of(CellType.Sea, CellType.Selected)
                    .contains(StaticGameInfoAccessor.getCellInPlayersGrid(startRow + range, column).getCellType())) {
                cellPointList.add(new CellPoint(column, startRow + range));
            } else {
                StaticGameInfoAccessor.appendGameLog("Other ship or dead zone interrupt placement");
                return false;
            }
        }
        finishPlacement(cellPointList);
        return true;
    }

    private boolean processHorizontalPlacement(int vectorColumn) {
        int startColumn = preparingShip.getColumn();
        int row = preparingShip.getRow();
        List<CellPoint> cellPointList = new ArrayList<>();

        int inc = vectorColumn > startColumn ? 1 : -1;
        for (int range = 0; Math.abs(range) < preparingShip.cellType.getRange(); range = range + inc) {
            if (startColumn + range < 1 || startColumn + range > currentGameSettings.getGameFieldSize()) {
                StaticGameInfoAccessor.appendGameLog("To close to game field border");
                return false;
            }
            if (List.of(CellType.Sea, CellType.Selected)
                    .contains(StaticGameInfoAccessor.getCellInPlayersGrid(row, startColumn + range).getCellType())) {
                cellPointList.add(new CellPoint(startColumn + range, row));
            } else {
                StaticGameInfoAccessor.appendGameLog("Other ship or dead zone interrupt placement");
                return false;
            }
        }
        finishPlacement(cellPointList);
        return true;
    }

    private void finishPlacement(List<CellPoint> cellPointList) {
        killCellsAroundCell(cellPointList);
        for (CellPoint cellPoint : cellPointList) {
            StaticGameInfoAccessor.getCellInPlayersGrid(cellPoint.getRow(), cellPoint.getColumn())
                    .setCellType(preparingShip.getCellType());
        }
    }

    private void killCellsAroundCell(CellPoint cellPoint) {
        StaticGameInfoAccessor.getPlayerGridPane().getChildren()
                .forEach((node) -> {
                    if (node instanceof GameFieldButton) {
                        GameFieldButton checkingCell = (GameFieldButton) node;
                        if (Math.abs(checkingCell.getRow() - cellPoint.getRow()) <= 1
                                && Math.abs(checkingCell.getColumn() - cellPoint.getColumn()) <= 1
                                && !CellType.getAllUnitsList().contains(checkingCell.getCellType())) {
                            checkingCell.setCellType(CellType.DeadZone);
                        }
                    }
                });
    }

    private void killCellsAroundCell(List<CellPoint> cellPointList) {
        for (CellPoint cellPoint : cellPointList) {
            killCellsAroundCell(cellPoint);
        }
    }

    private static class PreparingShip {
        private int column;
        private int row;
        private CellType cellType;

        public PreparingShip(int column, int row, CellType cellType) {
            this.column = column;
            this.row = row;
            this.cellType = cellType;
        }

        public int getColumn() {
            return column;
        }

        public int getRow() {
            return row;
        }

        public CellType getCellType() {
            return cellType;
        }
    }

    private static class CellPoint {
        private int column;
        private int row;

        public CellPoint(int column, int row) {
            this.column = column;
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public int getRow() {
            return row;
        }
    }
}
