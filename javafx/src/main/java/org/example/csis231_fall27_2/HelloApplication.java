package org.example.csis231_fall27_2;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setStage(stage);
        SceneManager.switchScene("login-view.fxml");
        stage.setTitle("CSIS 231");
        stage.show();
    }
}
