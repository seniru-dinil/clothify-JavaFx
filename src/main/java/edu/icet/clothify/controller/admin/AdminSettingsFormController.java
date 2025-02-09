package edu.icet.clothify.controller.admin;

import com.jfoenix.controls.JFXButton;
import edu.icet.clothify.dto.Admin;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.AdminService;
import edu.icet.clothify.util.PasswordUtil;
import edu.icet.clothify.util.dtoUtil.AdminUtil;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminSettingsFormController implements Initializable {

    @FXML
    private ImageView adminImg;

    @FXML
    private JFXButton btnBrowseImg;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtCurrentPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNewPassword;

    @FXML
    void btnBrowseImgOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        closeThisWindow(event);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (txtNewPassword.getText().trim().equals(txtConfirmPassword.getText().trim())){
            updateAdmin();
        }else{
//          AlertHelper.showPasswordMismatchError();
        }
    }

    public void updateAdmin(){
        Admin admin = new Admin(
                AdminUtil.getInstance().getAdminInstance().getId(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtEmail.getText(),
                PasswordUtil.getInstance().encryptPassword(txtConfirmPassword.getText().trim())
        );
        AdminService adminService = ServiceFactory.getInstance().getService(ServiceType.ADMIN);
        boolean b = adminService.updateAdmin(admin);
        if (b){
//            AlertHelper.showSuccessAlert("Update","Admin");
            AdminUtil.getInstance().setAdminInstance(admin);
        }else{
            System.out.println("error");
        }
    }

    public void closeThisWindow(Event event) {
        Node source = (Node) event.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtCurrentPassword.setEditable(false);
        Admin adminInstance = AdminUtil.getInstance().getAdminInstance();
        if (adminInstance!=null){
            txtFirstName.setText(adminInstance.getFirstName());
            txtLastName.setText(adminInstance.getLastName());
            txtEmail.setText(adminInstance.getEmail());
            txtCurrentPassword.setText(PasswordUtil.getInstance().decryptPassword(adminInstance.getPassword()));
        }
    }
}
