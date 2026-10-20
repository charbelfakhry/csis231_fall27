package org.example.csis231_fall27_2;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.csis231_fall27_2.api.ApiClient;
import org.example.csis231_fall27_2.api.LoginResponse;
import org.example.csis231_fall27_2.api.RegistrationResponse;

import java.io.IOException;
import java.time.LocalDate;

public class RegistrationController {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private ToggleGroup genderToggleGroup;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private DatePicker birthdatePicker;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() throws IOException
    {
        SceneManager.switchScene("login-view.fxml");
    }
    @FXML
    private void handleRegistration(){
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String username = usernameField.getText().trim();
        String gender = getSelectedGender();
        LocalDate birthdate = birthdatePicker.getValue();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if(firstName.isEmpty() || lastName.isEmpty() || username.isEmpty()){
            messageLabel.setText("Please fill in all the name fields!");
            return;
        }

        if(gender == null){
            messageLabel.setText("Please select your gender!");
            return;
        }

        if(birthdate == null){
            messageLabel.setText("Please select your birthdate!");
            return;
        }

        if(birthdate.isAfter(LocalDate.now())){
            messageLabel.setText("Birthdate cannot be in the future!");
            return;
        }

        if(password.length() < 4){
            messageLabel.setText("Password must be at least 4 characters!");
            return;
        }

        if(!password.equals(confirmPassword)){
            messageLabel.setText("Passwords do not match!");
            return;
        }

        messageLabel.setText("Registering...");

        // call the api in the background. prevent UI freezing.
        Task<RegistrationResponse> task = new Task<RegistrationResponse>() {
            @Override
            protected RegistrationResponse call() throws Exception {
                return ApiClient.register(username, password, firstName, lastName, gender, birthdate);
            }
        };

        task.setOnSucceeded(e -> {
            RegistrationResponse response = task.getValue();
            // registering also logs the user in
            Session.setCurrentUser(new LoginResponse(
                    response.id(),
                    response.username(),
                    response.firstName(),
                    response.lastName(),
                    response.role()
            ));
            try {
                SceneManager.switchScene("home-view.fxml");
            } catch (IOException ex) {
                messageLabel.setText("Could not open HomePage!");
            }
        });

        task.setOnFailed(e -> messageLabel.setText(task.getException().getMessage()));

        Thread thread = new Thread(task);
        thread.setDaemon(true); // don't keep the app alive after the window closes
        thread.start();
    }

    private String getSelectedGender(){
        RadioButton selected = (RadioButton) genderToggleGroup.getSelectedToggle();
        // userData holds the value the API expects (MALE / FEMALE), not the display text
        return selected == null ? null : (String) selected.getUserData();
    }

}
