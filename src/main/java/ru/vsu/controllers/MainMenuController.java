package ru.vsu.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import ru.vsu.FXEngine;
import ru.vsu.config.GameSettings;

/**
 * @author Ivan Rovenskiy
 * 16 December 2019
 */
public class MainMenuController {
    @FXML
    private ChoiceBox<GameType> gameTypeBox;

    @FXML
    void exitGameAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void startNewGameAction(ActionEvent event) {
        gameSettingsInitialize();

        FXEngine.setPrimaryScene(FXEngine.getGameFieldScene());
    }

    @FXML
    void initialize() {
        gameTypeBox.setItems(FXCollections.observableArrayList(GameType.values()));
        gameTypeBox.setValue(GameType.TestField);
        gameTypeBox.getStyleClass().add("choice-box-main-panel");
    }

    private void gameSettingsInitialize() {
        switch (gameTypeBox.getValue()) {
            case BigField:
                new GameSettings.Builder()
                        .setGameFieldSize(18)
                        .setShipWith_4_CellsCount(1)
                        .setShipWith_3_CellsCount(2)
                        .setShipWith_2_CellsCount(3)
                        .setShipWith_1_CellsCount(4)
                        .setMinesCount(3)
                        .setMineSweepersCount(1)
                        .setSubmarinesCount(1)
                        .build();
                break;
            case SmallField:
                new GameSettings.Builder()
                        .setGameFieldSize(10)
                        .setShipWith_4_CellsCount(1)
                        .setShipWith_3_CellsCount(2)
                        .setShipWith_2_CellsCount(3)
                        .setShipWith_1_CellsCount(4)
                        .setMinesCount(0)
                        .setMineSweepersCount(0)
                        .setSubmarinesCount(0)
                        .build();
                break;
            case MediumField:
                new GameSettings.Builder()
                        .setGameFieldSize(14)
                        .setShipWith_4_CellsCount(1)
                        .setShipWith_3_CellsCount(2)
                        .setShipWith_2_CellsCount(3)
                        .setShipWith_1_CellsCount(4)
                        .setMinesCount(3)
                        .setMineSweepersCount(1)
                        .setSubmarinesCount(1)
                        .build();
                break;
            case TestField:
                new GameSettings.Builder()
                        .setGameFieldSize(10)
                        .setShipWith_4_CellsCount(1)
                        .build();
                break;
        }
    }

    private enum GameType {
        BigField,
        MediumField,
        SmallField,
        TestField
    }
}
