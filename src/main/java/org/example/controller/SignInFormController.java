package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;
import org.example.dto.AdminDTO;
import org.example.dto.BranchDTO;
import org.example.dto.UserDTO;
import org.example.validation.Regex;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class SignInFormController {

    public TextField username;

    public ComboBox<String> cmbaccounttype;
    public PasswordField password;
    public PasswordField confirmpassword;
    public ComboBox<String> cmbbranch;
    public TextField email;

    public AnchorPane signUpPane;
 public AnchorPane loginPane;
    public TextField txtTel;
    public Label back;
    AdminBO adminBo = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);

    UserBO userBo= (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);


    public void initialize() {
        cmbaccounttype.getItems().addAll("Admin", "User");
        cmbbranch.setVisible(false);

        try {
            for (BranchDTO branchDTO : branchBO.getAll()) {
                cmbbranch.getItems().add(branchDTO.getLocation());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        /*cmbbranch.setOnAction(event -> {
            if (cmbaccounttype.getValue().equals("User")) {
                cmbbranch.setVisible(true);
            } else {
                cmbbranch.setVisible(false);
            }
        });*/
    }


    public void loginOnAction(MouseEvent mouseEvent) throws IOException {

        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Scene scene1 = new Scene(load);
            Stage stage1 = (Stage) loginPane.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Login Form");
            stage1.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void btnSignUpOnAction(ActionEvent actionEvent) throws IOException {
        String user = username.getText();
        String pass = password.getText();
        String type = cmbaccounttype.getValue();
        String email = this.email.getText();
        String rePassword = confirmpassword.getText();
        int tel = Integer.parseInt(txtTel.getText());

        if (user.isEmpty() || pass.isEmpty() || type.isEmpty() || email.isEmpty() || rePassword.isEmpty() || txtTel .getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else if (!pass.equals(rePassword)) {
            new Alert(Alert.AlertType.ERROR, "Password does not match").show();
        } else {
            if(Regex.validateEmail(email) && Regex.validatePassword(pass)){ if(type.equals("Admin")){
                try {
                    System.out.println("hello");
                    adminBo.save(new AdminDTO(user, email,tel, pass));
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Register Successful").show();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
                if (type.equals("User")) {
                    cmbbranch.setVisible(true);
                    if (cmbbranch.getValue() == null) {
                        new Alert(Alert.AlertType.ERROR, "Please select branch").show();
                    } else {
                        if(Regex.validateEmail(email) && Regex.validatePassword(pass)){
                            try {
                                BranchDTO branchDTO = branchBO.searchByLocation(cmbbranch.getValue());
                                userBo.save(new UserDTO(user, email, pass,tel, branchDTO));
                                new Alert(Alert.AlertType.CONFIRMATION, "Register Successful").show();
                            } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }else {
                            new Alert(Alert.AlertType.ERROR, " Your email or password is not valid :(").show();
                        }
                    }
                }
            }else{
                new Alert(Alert.AlertType.ERROR, " Your email or password is not valid :(").show();
            }

        }


       /* if (username.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty() || type.isEmpty() || txtTel.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else if (!password.equals(rePassword)) {
            new Alert(Alert.AlertType.ERROR, "Password does not match").show();
        } else {

            if (type.equals("User")) {
                if (cmbBranch.getValue() == null) {
                    new Alert(Alert.AlertType.ERROR, "Please select branch").show();
                } else {
                    try {
                        BranchDTO branchDTO = branchBO.searchByLocation(cmbBranch.getValue());
                        userBoImpl.save(new UserDTO(username, email, password,tel, branchDTO));
                        new Alert(Alert.AlertType.CONFIRMATION, "Register Successful").show();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

            } else {
                try {
                    adminBoImpl.save(new AdminDTO(username, email,tel, password));
                    new Alert(Alert.AlertType.CONFIRMATION, "Register Successful").show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        }




    }*/


    }

    public void cmbTypeOnAction(ActionEvent actionEvent) {
        if (cmbaccounttype.getValue().equals("User")) {
            cmbbranch.setVisible(true);
        } else {
            cmbbranch.setVisible(false);
        }
    }
private void clearFields(){

    username.clear();
    password.clear();
    confirmpassword.clear();
    email.clear();
}

    public void lblBackOnAction(MouseEvent mouseEvent) {
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Scene scene1 = new Scene(load);
            Stage stage1 = (Stage) back.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Login Form");
            stage1.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}







