package org.example.csis231_fall27_2;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.csis231_fall27_2.api.ApiClient;
import org.example.csis231_fall27_2.api.LoginResponse;

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

        if(username.isEmpty() || password.isEmpty()){
            messageLabel.setText("Please fill in all the fields!");
            return;
        }

        messageLabel.setText("Logging in...");

        // call the api in the background. prevent UI freezing.
        Task<LoginResponse> task = new Task<LoginResponse>() {
            @Override
            protected LoginResponse call() throws Exception {
                return ApiClient.login(username, password);
            }
        };

        task.setOnSucceeded(e -> {
            Session.setCurrentUser(task.getValue());
            try {
                SceneManager.switchScene("home-view.fxml");
            } catch (IOException ex) {
                messageLabel.setText("Could not open HomePage!");
            }
        });

        task.setOnFailed(e -> {
            messageLabel.setText(task.getException().getMessage());
            new Thread(task).start();
        });


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
