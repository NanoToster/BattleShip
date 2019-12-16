package ru.vsu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Ivan Rovenskiy
 * 16 December 2019
 */
public class FXEngine {
    private static Scene mainMenuScene;
    private static Scene gameFieldScene;
    private static Stage primaryStage;

    static void initFx(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setWidth(1280);
        primaryStage.setHeight(900);

        mainMenuScene = new Scene(FXEngine.loadFXML("main_menu.fxml"));
        gameFieldScene = new Scene(FXEngine.loadFXML("game_field.fxml"));
        gameFieldScene.getStylesheets().add("ru/vsu/styles/game_field.css");

        setPrimaryScene(mainMenuScene);
    }

    public static void setPrimaryScene(Scene primaryScene) {
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    private static Parent loadFXML(String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlFileName));
        return fxmlLoader.load();
    }

    public static Scene getMainMenuScene() {
        return mainMenuScene;
    }

    public static Scene getGameFieldScene() {
        return gameFieldScene;
    }
}
