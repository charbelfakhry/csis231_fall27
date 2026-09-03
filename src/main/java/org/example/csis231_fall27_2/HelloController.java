package org.example.csis231_fall27_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label msgTxt;

    @FXML
    protected void onGreetingButtonClick() {
        msgTxt.setText("test");
    }
}
