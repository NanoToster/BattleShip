package ru.vsu.utils;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * @author Ivan Rovenskiy
 * 18 December 2019
 */
public class StaticGameInfoAccessor {
    private static TextArea gameLogArea;
    private static GridPane playerGridPane;
    private static GridPane opponentGridPane;

    public static void appendGameLog(String message) {
        gameLogArea.appendText(message + "\n");
    }

    public static void setGameLogArea(TextArea gameLogArea) {
        StaticGameInfoAccessor.gameLogArea = gameLogArea;
    }

    public static GridPane getPlayerGridPane() {
        return playerGridPane;
    }

    public static void setPlayerGridPane(GridPane playerGridPane) {
        StaticGameInfoAccessor.playerGridPane = playerGridPane;
    }

    public static GridPane getOpponentGridPane() {
        return opponentGridPane;
    }

    public static void setOpponentGridPane(GridPane opponentGridPane) {
        StaticGameInfoAccessor.opponentGridPane = opponentGridPane;
    }
}
