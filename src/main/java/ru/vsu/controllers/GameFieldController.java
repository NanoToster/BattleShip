package ru.vsu.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ru.vsu.FXEngine;

/**
 * @author Ivan Rovenskiy
 * 16 December 2019
 */
public class GameFieldController {
    @FXML
    void backToMenuAction(ActionEvent event) {
        FXEngine.setPrimaryScene(FXEngine.getMainMenuScene());
    }
}
