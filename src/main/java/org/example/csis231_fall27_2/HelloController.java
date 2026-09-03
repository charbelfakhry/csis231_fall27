package org.example.csis231_fall27_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label goodbyeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onGodbyeButtonClick()
    {
        goodbyeText.setText("You are ready to leave");
    }
}
