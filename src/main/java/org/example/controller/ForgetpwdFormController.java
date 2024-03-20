package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.UserBO;
import org.example.dto.AdminDTO;

import java.io.IOException;

import static org.example.controller.LoginFormController.tempUserName;

public class ForgetpwdFormController {
    public TextField txtOTP;
    public TextField txtPassword;
    public TextField txtRePassword;
    public Button btnChangePassword;
    public Button btnBack;

    AdminBO adminBo = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene1 = new Scene(load);
        Stage stage1 = (Stage) btnBack.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Login Form");
        stage1.centerOnScreen();
    }

  /*  public void btnChangePasswordOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("BUTTON CLICKED");
        AdminDTO admin = adminBo.getAdmin(tempUserName);
        System.out.println(tempUserName);
        if(admin!= null){
            if(txtOTP.getText().equals(String.valueOf(LoginFormController.oneTimePassword))){
                boolean isPasswordValid = regexPatterns.isPasswordValid(txtPassword.getText());
                if(isPasswordValid){
                    if(txtPassword.getText().equals(txtRePassword.getText())){
                        final boolean isPasswordChanged = adminBo.updateAdmin(tempUserName,txtPassword.getText());
                        if(isPasswordChanged){
                            new Alert(Alert.AlertType.INFORMATION, "Password Changed | You will be redirected to Login Form", ButtonType.OK).showAndWait();
                            Parent load = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
                            Scene scene1 = new Scene(load);
                            Stage stage1 = (Stage) btnChangePassword.getScene().getWindow();
                            stage1.setScene(scene1);
                            stage1.setTitle("Login Form");
                            stage1.centerOnScreen();
                        }else{
                            new Alert(Alert.AlertType.ERROR, "Password Not Changed", ButtonType.OK).showAndWait();
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR, "Passwords do not match", ButtonType.OK).showAndWait();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR, "Password must contain at least one digit, one uppercase letter, one lowercase letter and one special character", ButtonType.OK).showAndWait();
                }
            }else{
                new Alert(Alert.AlertType.ERROR, "Invalid OTP", ButtonType.OK).showAndWait();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "User not found | redirecting..", ButtonType.OK).showAndWait();
            Parent load = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Scene scene1 = new Scene(load);
            Stage stage1 = (Stage) btnChangePassword.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Login Form");
            stage1.centerOnScreen();
        }
    }*/
}
