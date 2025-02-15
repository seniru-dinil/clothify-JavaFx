package edu.icet.clothify.controller.admin;


import com.jfoenix.controls.JFXButton;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.AdminService;
import edu.icet.clothify.util.AlertHelper;
import edu.icet.clothify.util.EmailUtil;
import edu.icet.clothify.util.OTPGenerator;
import edu.icet.clothify.util.Validation;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminForgotPasswordFormController implements Initializable {

    public JFXButton btnVerify;
    public JFXButton btnConfirm;
    public Label code;
    @FXML
    private Label lblEmail;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtOTP1;

    @FXML
    private TextField txtOTP2;

    @FXML
    private TextField txtOTP3;

    @FXML
    private TextField txtOTP4;

    private String currentOTP;

    @FXML
    void btnConfirmOnAction(ActionEvent event) throws IOException {
        boolean match = Validation.getInstance().isMatch(txtNewPassword.getText().trim(), txtConfirmPassword.getText().trim());
      if (match){
          AdminService adminService = ServiceFactory.getInstance().getService(ServiceType.ADMIN);
          boolean b = adminService.updateAdmin(txtEmail.getText().trim(), txtConfirmPassword.getText().trim());
          if (b) {
              loadLoginWindow(event);
          }else{
              AlertHelper.showErrorAlert("admin","invalid username");
          }
      }else{

      }
    }

    public void loadLoginWindow(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Object load = FXMLLoader.load(getClass().getResource("/view/admin/adminLoginForm.fxml"));
        currentStage.setScene(new Scene((Parent) load));
    }

    @FXML
    void btnSendOTPOnAction(ActionEvent event) {
        code.setText("We sent code to");
        lblEmail.setText(txtEmail.getText().trim());
        currentOTP = OTPGenerator.getInstance().generateOTP();
        boolean b = EmailUtil.sendOTPEmail(txtEmail.getText().trim(), currentOTP);
            btnVerify.setDisable(!b);
        System.out.println(b?"otp send successfully":"error");
    }

    public void btnVerifyOnAction(ActionEvent event) {
        String otp = txtOTP1.getText() +
                txtOTP2.getText() +
                txtOTP3.getText() +
                txtOTP4.getText();
        boolean equals = otp.equals(currentOTP);
        if (equals){
            txtNewPassword.setEditable(true);
            txtConfirmPassword.setEditable(true);
            btnConfirm.setDisable(false);
            btnConfirm.setStyle("-fx-background-color: #4F46E5;");
        }
        else{
            txtNewPassword.setEditable(false);
            txtConfirmPassword.setEditable(false);
            btnConfirm.setDisable(true);
            btnConfirm.setStyle("-fx-background-color:  #6366F1;");
            AlertHelper.showErrorAlert("otp","otp does not math each other");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtConfirmPassword.setEditable(false);
        txtNewPassword.setEditable(false);
        btnVerify.setDisable(true);
        btnConfirm.setDisable(true);
    }
}
