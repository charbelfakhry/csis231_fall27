package org.example.csis231_fall27_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin(){
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(username.equals("admin") && password.equals("1234")){
            messageLabel.setText("Login Successful!");
        }else{
            messageLabel.setText("Invalid username or password!");
        }
    }

    @FXML
    private void handleClear(){
        usernameField.clear();
        passwordField.clear();
        messageLabel.setText("");
    }



}
