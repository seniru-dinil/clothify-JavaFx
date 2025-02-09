package edu.icet.clothify.controller.forms;

import edu.icet.clothify.dto.Employee;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.EmployeeService;
import edu.icet.clothify.util.PasswordUtil;
import edu.icet.clothify.util.Validation;
import edu.icet.clothify.util.dtoUtil.EmployeeUtil;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class AddEmployeeFormController {

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
       closeWindow(event);
    }

    @FXML
    void btnSaveEmployeeOnAction(ActionEvent event) throws SQLException {
        if(Validation.getInstance().isMatch(txtPassword.getText(),txtConfirmPassword.getText())){
            Employee employee = new Employee(
                    0,
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtEmail.getText(),
                    LocalDateTime.now(),
                    PasswordUtil.getInstance().encryptPassword(txtPassword.getText().trim())
            );
            EmployeeService service = ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
            service.addEmployee(employee);
            EmployeeUtil.getInstance().loadContainer();
            closeWindow(event);
        }else{
//            AlertHelper.showPasswordMismatchError();
        }
    }

    public void closeWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }

}
