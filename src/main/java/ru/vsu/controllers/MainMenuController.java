package ru.vsu.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ru.vsu.FXEngine;

/**
 * @author Ivan Rovenskiy
 * 16 December 2019
 */
public class MainMenuController {
    @FXML
    void exitGameAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void startNewGameAction(ActionEvent event) {
        FXEngine.setPrimaryScene(FXEngine.getGameFieldScene());
    }
}
