package org.example.csis231_fall27_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label msgTxt;

    @FXML
    protected void onGreetingButtonClick(ActionEvent event) {
        String lbl = ((Button)event.getTarget()).getText();
        if(lbl.equals("Hello"))
        {
            msgTxt.setText("Welcome to our website");
        }else{
            msgTxt.setText("Have a safe trip.");
        }
    }
}
