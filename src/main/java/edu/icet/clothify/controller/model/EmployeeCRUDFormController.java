package edu.icet.clothify.controller.model;


import edu.icet.clothify.component.tableCard.EmployeeTableCard;
import edu.icet.clothify.dto.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeCRUDFormController implements Initializable {
    public VBox employeeContainer;
    ArrayList<Employee> employees;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadEmployees();
        populateEmployeeCards();
    }


    private void loadEmployees() {
        employees = new ArrayList<>();
        employees.add(
                new Employee(1, "Saman", "Kumara", "samankumara@gmail.com", "2023/10/13", "password123")
        );
    }

    private void populateEmployeeCards() {
        employeeContainer.getChildren().clear();

        for (Employee employee : employees) {
            AnchorPane anchorPane = EmployeeTableCard.getInstance().createAnchorPane(employee);
            employeeContainer.getChildren().add(anchorPane);
        }
    }

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/add.forms/addEmployeeForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }
}
