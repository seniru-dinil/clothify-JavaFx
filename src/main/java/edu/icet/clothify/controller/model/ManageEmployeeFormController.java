package edu.icet.clothify.controller.model;


import edu.icet.clothify.dto.Employee;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.EmployeeService;
import edu.icet.clothify.util.dtoUtil.EmployeeUtil;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class ManageEmployeeFormController implements Initializable {
    public VBox employeeContainer;
    public TextField searchBarEmployee;


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

    public void searchBarOnAction(KeyEvent keyEvent) {
        EmployeeService employeeService = ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
        List<Employee> employeesByName = employeeService.getEmployeesByName(searchBarEmployee.getText());
        EmployeeUtil.getInstance().populateEmployeeCards(employeesByName);
    }
}
