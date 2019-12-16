package ru.vsu;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Ivan Rovenskiy
 * 16 December 2019
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXEngine.initFx(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
