package edu.icet.clothify.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertHelper {


    public static void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public static void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("Error!");
        alert.setContentText(message);
        alert.showAndWait();
    }


    public static boolean showConfirmationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText("Please confirm");
        alert.setContentText(message);

        return alert.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }


    public static void showAddedAlert(String entityName) {
        showSuccessAlert("Success", entityName + " has been added successfully!");
    }

    public static void showUpdatedAlert(String entityName) {
        showSuccessAlert("Success", entityName + " has been updated successfully!");
    }

    public static void showDeletedAlert(String entityName) {
        showSuccessAlert("Success", entityName + " has been deleted successfully!");
    }

    public static void showDeleteErrorAlert(String entityName) {
        showErrorAlert("Deletion Failed", "Failed to delete " + entityName + ". Please try again.");
    }
}
