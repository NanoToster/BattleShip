package ru.vsu.config;

import ru.vsu.exceptions.CommonBaseException;

/**
 * @author Ivan Rovenskiy
 * 16 December 2019
 */
public class CurrentGameSettings {
    private static CurrentGameSettings currentGameSettings;
    private int gameFieldSize;

    private int shipWith_4_CellsCount;
    private int shipWith_3_CellsCount;
    private int shipWith_2_CellsCount;
    private int shipWith_1_CellsCount;
    private int minesCount;
    private int submarinesCount;
    private int minesweeperCount;

    private CurrentGameSettings() {
    }

    public static CurrentGameSettings getCurrentGameSettings() {
        return currentGameSettings;
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

    public static class Builder {
        private CurrentGameSettings delegate;

        public Builder() {
            delegate = new CurrentGameSettings();
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

        public CurrentGameSettings build() {
            if (delegate != null) {
                currentGameSettings = delegate;
                delegate = null;
                return currentGameSettings;
            } else {
                throw new CommonBaseException.BuilderReuseException("CurrentGameSettings builder reusing");
            }
        }
    }
}
