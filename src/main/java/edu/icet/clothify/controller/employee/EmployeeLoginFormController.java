package edu.icet.clothify.controller.employee;

import edu.icet.clothify.dto.Employee;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.EmployeeService;
import edu.icet.clothify.util.AlertHelper;
import edu.icet.clothify.util.PasswordUtil;
import edu.icet.clothify.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeLoginFormController {

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnForgotPasswordOnAction(MouseEvent event) {
           closeThisWindow(event);
    }

    @FXML
    void btnSigninOnAction(ActionEvent event) throws IOException {
        getEmployee(event);
    }

    public void closeThisWindow(Event event){
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }

    private void setUpHome(ActionEvent event) throws IOException {
        closeThisWindow(event);
        Object load = FXMLLoader.load(getClass().getResource("/view/employee/employeeHomepageForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }

    private void getEmployee(ActionEvent event) throws IOException {
        EmployeeService employeeService = ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
        Employee employee = employeeService.getEmployee(txtEmail.getText().trim());
        if(employee==null){
            AlertHelper.showAlert(Alert.AlertType.ERROR,"username error","invalid username");
        }else{
            String s = PasswordUtil.getInstance().decryptPassword(employee.getPassword());
            if(s.equals(txtPassword.getText().trim())){
                setUpHome(event);
            }else{
                AlertHelper.showPasswordMismatchError();
            }
        }
    }

}
