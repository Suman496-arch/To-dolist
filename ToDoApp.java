package org.example.demo7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ToDoApp extends Application {

    private final ToDoList toDoList = new ToDoList();
    private final ListView<String> listView = new ListView<>();
    private final TextField inputField = new TextField();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("To-Do List");

        Button addButton = new Button("Add");
        Button removeButton = new Button("Remove");
        Button editButton = new Button("Edit");

        HBox buttons = new HBox(10, addButton, removeButton, editButton);
        VBox layout = new VBox(10, inputField, buttons, listView);
        layout.setPadding(new javafx.geometry.Insets(10));


        addButton.setOnAction(e -> {
            try {
                toDoList.addItem(inputField.getText());
                refreshList();
                inputField.clear();
            } catch (IllegalArgumentException ex) {
                showAlert("Input Error", "The text field cannot be empty");
            }
        });

        removeButton.setOnAction(e -> {
            int selected = listView.getSelectionModel().getSelectedIndex();
            try {
                toDoList.removeItem(selected);
                refreshList();
                inputField.clear();
            } catch (IndexOutOfBoundsException ex) {
                showAlert("Remove Error", "Please select an item to remove.");
            }
        });

        editButton.setOnAction(e -> {
            int selected = listView.getSelectionModel().getSelectedIndex();
            String newText = inputField.getText();

            try {
                toDoList.editItem(selected, newText);
                refreshList();
                inputField.clear();
            } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
                showAlert("Edit Error", "Please select the tasks to edit");
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                inputField.setText(newVal);
            }
        });

        primaryStage.setScene(new Scene(layout, 400, 300));
        primaryStage.show();
    }

    private void refreshList() {
        listView.getItems().setAll(toDoList.getItems());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

