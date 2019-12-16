package ru.vsu.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import ru.vsu.FXEngine;
import ru.vsu.config.CurrentGameSettings;

/**
 * @author Ivan Rovenskiy
 * 16 December 2019
 */
public class GameFieldController {
    @FXML
    private GridPane playerGridPane;
    @FXML
    private GridPane opponentGridPane;
    @FXML
    private TextArea battleLogTextArea;

    @FXML
    void backToMenuAction(ActionEvent event) {
        FXEngine.setPrimaryScene(FXEngine.getMainMenuScene());
    }

    @FXML
    void initialize() {
        initPlayersGridPane(playerGridPane);
        addEmptyCellsToGridPane(playerGridPane);

        initPlayersGridPane(opponentGridPane);
        addEmptyCellsToGridPane(opponentGridPane);
    }

    private void initPlayersGridPane(GridPane gridPane) {
        gridPane.getColumnConstraints().removeAll(gridPane.getColumnConstraints());
        gridPane.getRowConstraints().removeAll(gridPane.getRowConstraints());

        final CurrentGameSettings currentGameSettings = CurrentGameSettings.getCurrentGameSettings();
        for (int column = 0; column < currentGameSettings.getGameFieldSize() + 1; column++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints() {{
                setPercentWidth(100d / (currentGameSettings.getGameFieldSize() + 1));
                setHalignment(HPos.CENTER);
            }});
            gridPane.getRowConstraints().add(new RowConstraints() {{
                setPercentHeight(100d / (currentGameSettings.getGameFieldSize() + 1));
                setValignment(VPos.CENTER);
            }});
            if (column > 0) {
                gridPane.add(new Label(String.valueOf(column)), column, 0);
                gridPane.add(new Label(String.valueOf(column)), 0, column);
            }
        }
    }

    private void addEmptyCellsToGridPane(GridPane gridPane) {
        final CurrentGameSettings currentGameSettings = CurrentGameSettings.getCurrentGameSettings();
        for (int row = 1; row < currentGameSettings.getGameFieldSize() + 1; row++) {
            for (int column = 1; column < currentGameSettings.getGameFieldSize() + 1; column++) {
                gridPane.add(new Button() {{
                    setMaxHeight(Double.MAX_VALUE);
                    setMaxWidth(Double.MAX_VALUE);
                    getStyleClass().add("common-grid-cell");
                    setOnMouseEntered(mouseEvent -> getStyleClass().add("under-mouse-grid-cell"));
                    setOnMouseExited(mouseEvent -> getStyleClass().remove("under-mouse-grid-cell"));
                }}, column, row);
            }
        }
    }
}
