package org.example.csis231_fall27_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomeController {

    @FXML
    private Label welcomeMsg;

    @FXML
    private void initialize(){
        if(Session.getCurrentUser() != null){
            welcomeMsg.setText("Welcome "+
                    Session.getCurrentUser().firstName() +
                    " ("+Session.getCurrentUser().role()+")"
            );


        }
    }


    @FXML
    private void handleLogout() throws IOException
    {
        Session.clear();
        SceneManager.switchScene("login-view.fxml");
    }

    @FXML
    private void handleStudents() throws IOException
    {
        SceneManager.switchScene("students-view.fxml");
    }

    @FXML
    private void handleCourses() throws IOException
    {
        System.out.println("Handle courses clicked!");
    }

    @FXML
    private void handleReports() throws IOException
    {
        System.out.println("Handle Reports clicked!");
    }
}
