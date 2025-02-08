package edu.icet.clothify.controller.model;


import com.jfoenix.controls.JFXComboBox;
import edu.icet.clothify.dto.Product;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.ProductService;
import edu.icet.clothify.util.dtoUtil.ProductUtil;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageProductFormController implements Initializable {


    public VBox productContainer;
    public JFXComboBox cmbCategories;
    public JFXComboBox cmbStatus;
    public TextField searchBar;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProductUtil instance = ProductUtil.getInstance();
        instance.initializeProductContainer(productContainer);
        instance.getAllData();
        setValuesToCombos();
        cmbCategories.setOnAction(e -> {
            ProductUtil.getInstance().getCategoryData(cmbCategories.getSelectionModel().getSelectedItem().toString());
        });
        cmbStatus.setOnAction(e -> {
            ProductUtil.getInstance().getProductsByStatus(cmbStatus.getSelectionModel().getSelectedItem().toString());
        });
    }

    public void setValuesToCombos(){
        ObservableList<String> categories = FXCollections.observableArrayList();
        categories.add("kids");
        categories.add("gents");
        categories.add("ladies");
        categories.add("all");
        cmbCategories.setItems(categories);
        cmbCategories.setOnAction(e -> {

        });

        ObservableList<String> status = FXCollections.observableArrayList();
        status.add("in stock");
        status.add("out of stock");
        cmbStatus.setItems(status);
    }

    public void btnAddProductOnAction(ActionEvent actionEvent) throws IOException {
        Node load = (Node) FXMLLoader.load(getClass().getResource("/view/add.forms/addProductForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }

    public void searchBarOnAction(KeyEvent keyEvent) {
        ProductService productService = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        List<Product> productsByName = productService.getProductsByName(searchBar.getText());
        System.out.println(productsByName);
        ProductUtil.getInstance().populateProductContainer(productsByName);
    }
}
