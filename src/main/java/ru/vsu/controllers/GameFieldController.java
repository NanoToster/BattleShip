package ru.vsu.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    void backToMenuAction(ActionEvent event) {
        FXEngine.setPrimaryScene(FXEngine.getMainMenuScene());
    }

    @FXML
    void initialize() {
        initPane(playerGridPane);
    }

    private void initPane(GridPane gridPane) {
        gridPane.getColumnConstraints().removeAll(gridPane.getColumnConstraints());
        gridPane.getRowConstraints().removeAll(gridPane.getRowConstraints());

        final CurrentGameSettings currentGameSettings = CurrentGameSettings.getCurrentGameSettings();
        for (int column = 0; column < currentGameSettings.getGameFieldSize() + 1; column++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints() {{
                setPercentWidth(100d / (currentGameSettings.getGameFieldSize() + 1));
            }});
            gridPane.getRowConstraints().add(new RowConstraints() {{
                setPercentHeight(100d / (currentGameSettings.getGameFieldSize() + 1));
            }});
            if (column > 0) {
                gridPane.add(new Label(String.valueOf(column)), column, 0);
                gridPane.add(new Label(String.valueOf(column)), 0, column);
            }
        }
    }
}
