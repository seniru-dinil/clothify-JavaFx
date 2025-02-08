package edu.icet.clothify.controller.forms;

import com.jfoenix.controls.JFXComboBox;
import edu.icet.clothify.dto.Product;
import edu.icet.clothify.dto.Supplier;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.ProductService;
import edu.icet.clothify.service.custom.SupplierService;
import edu.icet.clothify.util.dtoUtil.ProductUtil;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddProductFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbCategory;

    @FXML
    private JFXComboBox<String> cmbSupplier;

    @FXML
    private ImageView imgBox;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtStock;

    private String imagePath;


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
            imagePath = file.toURI().toString();
            Image image = new Image(file.toURI().toString());
            imgBox.setImage(image);
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        closeWindow(event);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        ProductService productService = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        productService.addProduct(new Product(
                0,
                txtProductName.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtStock.getText()),
                imagePath,
                Integer.parseInt(cmbCategory.getSelectionModel().getSelectedItem().split(" ")[0]),
                Integer.parseInt(cmbSupplier.getSelectionModel().getSelectedItem().split(" ")[1])
                ,
                ""
        ));
        ProductUtil.getInstance().getAllData();
        closeWindow(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValuesToCombos();
    }

    private void setValuesToCombos() {
        SupplierService supplierService = ServiceFactory.getInstance().getService(ServiceType.SUPPLIER);
        List<Supplier> allSuppliers = supplierService.getAllSuppliers();
        ObservableList<String> objects = FXCollections.observableArrayList();
        allSuppliers.forEach(supplier -> objects.add(supplier.getSupplierName() + " " + supplier.getSupplierId()));
        cmbSupplier.setItems(objects);

        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("3 Ladies");
        list.add("2 Gents");
        list.add("1 Kids");
        cmbCategory.setItems(list);
    }

    private void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }
}
