package edu.icet.clothify.controller.model;


import edu.icet.clothify.util.EmployeeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeCRUDFormController implements Initializable {
    public VBox employeeContainer;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmployeeUtil instance = EmployeeUtil.getInstance();
        instance.initializeContainer(employeeContainer);
        instance.loadContainer();
    }


    public void btnAddEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/add.forms/addEmployeeForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }
}
