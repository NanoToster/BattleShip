package ru.vsu.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import ru.vsu.FXEngine;
import ru.vsu.components.GameFieldButton;
import ru.vsu.components.GameFieldButton.FieldType;
import ru.vsu.config.CurrentGameSettings;
import ru.vsu.domain.CellType;
import ru.vsu.game_engine.GameBattleEngine;
import ru.vsu.game_engine.GameEngine;
import ru.vsu.game_engine.GamePrepareEngine;
import ru.vsu.utils.StaticGameInfoAccessor;

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
    private TextArea gameLogArea;

    private GameEngine gameEngine;

    @FXML
    void backToMenuAction(ActionEvent event) {
        FXEngine.setPrimaryScene(FXEngine.getMainMenuScene());
    }

    @FXML
    void initialize() {
        gameEngine = new GamePrepareEngine();
        StaticGameInfoAccessor.setGameLogArea(gameLogArea);
        StaticGameInfoAccessor.setPlayerGridPane(playerGridPane);
        StaticGameInfoAccessor.setOpponentGridPane(opponentGridPane);

        initGridPane(playerGridPane);
        addEmptyCellsToPlayerGridPane(playerGridPane);

        initGridPane(opponentGridPane);
        addEmptyCellsToEnemyGridPane(opponentGridPane);
    }

    private void initGridPane(GridPane gridPane) {
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

    private void addEmptyCellsToPlayerGridPane(GridPane gridPane) {
        final CurrentGameSettings currentGameSettings = CurrentGameSettings.getCurrentGameSettings();
        for (int row = 1; row < currentGameSettings.getGameFieldSize() + 1; row++) {
            for (int column = 1; column < currentGameSettings.getGameFieldSize() + 1; column++) {
                gridPane.add(new GameFieldButton(column, row, FieldType.Player) {{
                    setCellType(CellType.Sea);
                    setMaxHeight(Double.MAX_VALUE);
                    setMaxWidth(Double.MAX_VALUE);
                    setOnMouseEntered(mouseEvent -> getStyleClass().add("under-mouse-grid-cell"));
                    setOnMouseExited(mouseEvent -> getStyleClass().remove("under-mouse-grid-cell"));
                    setOnMousePressed(mouseEvent -> {
                        if (gameEngine.getGameStatus().equals(GameEngine.GameStatus.PreparingEnd)) {
                            gameEngine = new GameBattleEngine();
                        }
                        gameEngine.processGameFieldButtonClick(this);
                    });
                }}, column, row);
            }
        }
    }

    private void addEmptyCellsToEnemyGridPane(GridPane gridPane) {
        final CurrentGameSettings currentGameSettings = CurrentGameSettings.getCurrentGameSettings();
        for (int row = 1; row < currentGameSettings.getGameFieldSize() + 1; row++) {
            for (int column = 1; column < currentGameSettings.getGameFieldSize() + 1; column++) {
                gridPane.add(new GameFieldButton(column, row, FieldType.Enemy) {{
                    setCellType(CellType.Sea);
                    setMaxHeight(Double.MAX_VALUE);
                    setMaxWidth(Double.MAX_VALUE);
                    setOnMouseEntered(mouseEvent -> getStyleClass().add("under-mouse-grid-cell"));
                    setOnMouseExited(mouseEvent -> getStyleClass().remove("under-mouse-grid-cell"));
                    setOnMousePressed(mouseEvent -> gameEngine.processGameFieldButtonClick(this));
                }}, column, row);
            }
        }
    }
}
