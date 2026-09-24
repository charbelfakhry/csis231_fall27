package org.example.csis231_fall27_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() throws IOException{
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(username.equals("admin") && password.equals("1234")){
            SceneManager.switchScene("home-view.fxml");
        }else{
            messageLabel.setText("Invalid username or password!");
        }
    }

    @FXML
    private void handleRegister() throws IOException
    {
        SceneManager.switchScene("registration-view.fxml");
    }

    @FXML
    private void handleClear(){
        usernameField.clear();
        passwordField.clear();
        messageLabel.setText("");
    }



}
