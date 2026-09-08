package org.example.csis231_fall27_2;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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

        messageLabel.setText("Welcome " + firstName + " " + lastName + ", you are registered!");
    }

    private String getSelectedGender(){
        RadioButton selected = (RadioButton) genderToggleGroup.getSelectedToggle();
        return selected == null ? null : selected.getText();
    }

}
