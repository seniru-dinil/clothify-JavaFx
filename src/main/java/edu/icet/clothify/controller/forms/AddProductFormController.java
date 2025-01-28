package edu.icet.clothify.controller.forms;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AddProductFormController {

    @FXML
    private JFXComboBox<?> cmbCategory;

    @FXML
    private JFXComboBox<?> cmbSupplier;

    @FXML
    private ImageView imgBox;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtStock;

    @FXML
    void btnBrowseOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an Image");

        // Set File Extension Filters
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Show Open Dialog
        Node source = (Node) (event.getSource());
        Stage window = (Stage) source.getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);

        if (file != null) {
            // Load and display the image
            Image image = new Image(file.toURI().toString());
            imgBox.setImage(image);
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

}
