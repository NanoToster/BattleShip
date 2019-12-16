package ru.vsu.config;

import ru.vsu.exceptions.CommonBaseException;

/**
 * @author Ivan Rovenskiy
 * 16 December 2019
 */
public class CurrentGameSettings {
    private static CurrentGameSettings currentGameSettings;
    private int gameFieldSize;

    private CurrentGameSettings() {
    }

    public static CurrentGameSettings getCurrentGameSettings() {
        return currentGameSettings;
    }

    public int getGameFieldSize() {
        return gameFieldSize;
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
