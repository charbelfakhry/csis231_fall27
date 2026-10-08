package org.example.csis231_fall27_2;

import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.csis231_fall27_2.api.ApiClient;
import org.example.csis231_fall27_2.api.StudentResponse;

import java.io.IOException;
import java.util.List;

public class StudentController {
    @FXML private TableView<StudentResponse> studentTable;
    @FXML private TableColumn<StudentResponse, Long> idColumn;
    @FXML private TableColumn<StudentResponse, String> firstNameColumn;
    @FXML private TableColumn<StudentResponse, String> lastNameColumn;
    @FXML private TableColumn<StudentResponse, String> emailColumn;
    @FXML private TableColumn<StudentResponse, String> departmentColumn;
    @FXML private TableColumn<StudentResponse, String> statusColumn;
    @FXML private Label messageLabel;

    @FXML
    private void initialize(){
        // data binding
        idColumn.setCellValueFactory(c->
                new SimpleObjectProperty<>(c.getValue().id()));
        firstNameColumn.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().firstName()));
        lastNameColumn.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().lastName()));
        emailColumn.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().email()));
        departmentColumn.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().departmentName()));
        statusColumn.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().status()));

        loadStudents();
    }

    @FXML
    private void loadStudents(){
        messageLabel.setText("Loading students...");
        Task<List<StudentResponse>> task = new Task<List<StudentResponse>>() {
            @Override
            protected List<StudentResponse> call() throws Exception {

                return ApiClient.getStudents();
            }
        };

        task.setOnSucceeded(e -> {
            studentTable.getItems().setAll(task.getValue());
            messageLabel.setText(task.getValue().size() + " students loaded!");
        });

        task.setOnFailed(e -> {
            messageLabel.setText(task.getException().getMessage());
            new Thread(task).start();
        });
    }

    @FXML
    private void handleBack() throws IOException {
        SceneManager.switchScene("home-vew.fxml");
    }
}
