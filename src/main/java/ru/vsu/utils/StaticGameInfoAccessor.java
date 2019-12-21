package ru.vsu.utils;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import ru.vsu.components.GameFieldButton;

import java.util.Optional;

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

    public static GridPane getOpponentGridPane() {
        return opponentGridPane;
    }

    public static GameFieldButton getCellInPlayersGrid(final int row, final int column) {
        return getCellFromGrid(playerGridPane, row, column);
    }

    public static GameFieldButton getCellInOpponentsGrid(final int row, final int column) {
        return getCellFromGrid(opponentGridPane, row, column);
    }

    private static GameFieldButton getCellFromGrid(final GridPane gridPane, final int row, final int column) {
        final Optional<Node> nodeFromPlayersGridOptional = gridPane.getChildren().stream()
                .filter(node -> GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row)
                .findFirst();

        return (GameFieldButton) nodeFromPlayersGridOptional.orElse(null);
    }

    public static void setPlayerGridPane(GridPane playerGridPane) {
        StaticGameInfoAccessor.playerGridPane = playerGridPane;
    }

    public static void setOpponentGridPane(GridPane opponentGridPane) {
        StaticGameInfoAccessor.opponentGridPane = opponentGridPane;
    }
}
