package ru.vsu.config;

import ru.vsu.domain.CellType;
import ru.vsu.exceptions.CommonBaseException;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Ivan Rovenskiy
 * 16 December 2019
 */
public class GameSettings {
    private static GameSettings gameSettings;
    private int gameFieldSize;

    private int shipWith_4_CellsCount;
    private int shipWith_3_CellsCount;
    private int shipWith_2_CellsCount;
    private int shipWith_1_CellsCount;
    private int minesCount;
    private int submarinesCount;
    private int minesweeperCount;
    private Queue<CellType> shipPlacementQueue;

    private GameSettings() {
    }

    public static GameSettings getGameSettings() {
        return gameSettings;
    }

    public int getGameFieldSize() {
        return gameFieldSize;
    }

    public int getShipWith_4_CellsCount() {
        return shipWith_4_CellsCount;
    }

    public int getShipWith_3_CellsCount() {
        return shipWith_3_CellsCount;
    }

    public int getShipWith_2_CellsCount() {
        return shipWith_2_CellsCount;
    }

    public int getShipWith_1_CellsCount() {
        return shipWith_1_CellsCount;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public int getSubmarinesCount() {
        return submarinesCount;
    }

    public int getMinesweeperCount() {
        return minesweeperCount;
    }

    public Queue<CellType> getShipPlacementQueue() {
        return shipPlacementQueue;
    }

    public static class Builder {
        private GameSettings delegate;

        public Builder() {
            delegate = new GameSettings();
        }

        public Builder setGameFieldSize(int gameFieldSize) {
            delegate.gameFieldSize = gameFieldSize;
            return this;
        }

        public Builder setShipWith_4_CellsCount(int shipWith_4_cellsCount) {
            delegate.shipWith_4_CellsCount = shipWith_4_cellsCount;
            return this;
        }

        public Builder setShipWith_3_CellsCount(int shipWith_3_cellsCount) {
            delegate.shipWith_3_CellsCount = shipWith_3_cellsCount;
            return this;
        }

        public Builder setShipWith_2_CellsCount(int shipWith_2_cellsCount) {
            delegate.shipWith_2_CellsCount = shipWith_2_cellsCount;
            return this;
        }

        public Builder setShipWith_1_CellsCount(int shipWith_1_cellsCount) {
            delegate.shipWith_1_CellsCount = shipWith_1_cellsCount;
            return this;
        }

        public Builder setMinesCount(int minesCount) {
            delegate.minesCount = minesCount;
            return this;
        }

        public Builder setSubmarinesCount(int submarinesCount) {
            delegate.submarinesCount = submarinesCount;
            return this;
        }

        public Builder setMineSweepersCount(int mineSweepersCount) {
            delegate.minesweeperCount = mineSweepersCount;
            return this;
        }

        public GameSettings build() {
            if (delegate != null) {
                setShipPlacementQueue();
                gameSettings = delegate;
                delegate = null;
                return gameSettings;
            } else {
                throw new CommonBaseException.BuilderReuseException("CurrentGameSettings builder reusing");
            }
        }

        private void setShipPlacementQueue() {
            delegate.shipPlacementQueue = new ArrayDeque<>();
            for (int i = 0; i < delegate.shipWith_4_CellsCount; i++) {
                delegate.shipPlacementQueue.offer(CellType.CommonShip_4);
            }
            for (int i = 0; i < delegate.shipWith_3_CellsCount; i++) {
                delegate.shipPlacementQueue.offer(CellType.CommonShip_3);
            }
            for (int i = 0; i < delegate.shipWith_2_CellsCount; i++) {
                delegate.shipPlacementQueue.offer(CellType.CommonShip_2);
            }
            for (int i = 0; i < delegate.shipWith_1_CellsCount; i++) {
                delegate.shipPlacementQueue.offer(CellType.CommonShip_1);
            }
            for (int i = 0; i < delegate.submarinesCount; i++) {
                delegate.shipPlacementQueue.offer(CellType.Submarine);
            }
            for (int i = 0; i < delegate.minesCount; i++) {
                delegate.shipPlacementQueue.offer(CellType.Mine);
            }
            for (int i = 0; i < delegate.minesweeperCount; i++) {
                delegate.shipPlacementQueue.offer(CellType.Minesweeper);
            }
        }
    }
}
