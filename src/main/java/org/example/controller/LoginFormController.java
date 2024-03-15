package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.UserBO;
import org.example.dto.AdminDTO;
import org.example.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class LoginFormController {
    public TextField username;
    public PasswordField password;
    public ComboBox<String> cmbaccounttype;

    public AnchorPane loginPane;
    public Button signin;
    public Button login;

    public static String loggedUserEmail;
    AdminBO adminBoImpl = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);

    UserBO userBoImpl = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);


    public void initialize() {
        cmbaccounttype.getItems().addAll("Admin", "User");
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String user = username.getText();
        String pass = password.getText();
        String type = cmbaccounttype.getValue();

        if(type.equals("Admin")) {
            try {
                List<AdminDTO> all = adminBoImpl.getAll();
                for (AdminDTO adminDTO : all) {
                    if (adminDTO.getName().equals(user) && adminDTO.getPassword().equals(pass)) {
                        new Alert(Alert.AlertType.INFORMATION, "Welcome Admin " + user).show();
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/view/admin/dashboard_form.fxml"));
                            Scene scene1 = new Scene(root);
                            Stage stage1 = (Stage) login.getScene().getWindow();
                            stage1.setScene(scene1);
                            stage1.setTitle("Sign Up Form");
                            stage1.centerOnScreen();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR, "Invalid Admin credentials..").show();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            try {
                List<UserDTO> allUsers = userBoImpl.getAll();
                for(UserDTO userDTO:allUsers){
                    if(userDTO.getName().equals(user) && userDTO.getPassword().equals(pass)){
                        new Alert(Alert.AlertType.INFORMATION, "Welcome User " + user).show();
                        loggedUserEmail=userDTO.getEmail();
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/view/user/userDash_form.fxml"));
                            Scene scene1 = new Scene(root);
                            Stage stage1 = (Stage) login.getScene().getWindow();
                            stage1.setScene(scene1);
                            stage1.setTitle("Sign Up Form");
                            stage1.centerOnScreen();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR, "Invalid User credentials..").show();
                    }
                }
            }catch (Exception e){
                System.out.println(e);
            }

        }
    }




        public void btnSignUpOnAction (ActionEvent actionEvent) throws IOException {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/signin_form.fxml"));
                Scene scene1 = new Scene(root);
                Stage stage1 = (Stage)signin .getScene().getWindow();
                stage1.setScene(scene1);
                stage1.setTitle("Sign Up Form");
                stage1.centerOnScreen();


            }catch (Exception e){
                System.out.println(e);
            }


        }
}
