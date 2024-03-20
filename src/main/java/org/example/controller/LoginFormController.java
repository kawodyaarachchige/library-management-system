package org.example.controller;

import animatefx.animation.SlideInUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.UserBO;
import org.example.dto.AdminDTO;
import org.example.dto.UserDTO;
import org.example.validation.Mail;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class LoginFormController {
    public TextField username;
    public PasswordField password;
    public ComboBox<String> cmbaccounttype;

    public AnchorPane loginPane;
    public Button signin;
    public Button login;

    public static String email;

    public static String loggedUserEmail;

    public static String loggedUserName;
    public TextField textview;
    public ImageView imgLock;
    public ImageView imgView;

    public Label txtForgetPassword;

    public static String tempUserName;

    public static int oneTimePassword;
    AdminBO adminBo = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);

    UserBO userBo = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);


    public void initialize() {
        cmbaccounttype.getItems().addAll("Admin", "User");
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String user = username.getText();
        String pass = password.getText();
        String type = cmbaccounttype.getValue();

        if(type.equals("Admin")) {
            try {
                boolean isCredentialsOK=false;
                List<AdminDTO> all = adminBo.getAll();
                for (AdminDTO adminDTO : all) {
                    if (adminDTO.getName().equals(user) && adminDTO.getPassword().equals(pass)) {
                        new Alert(Alert.AlertType.INFORMATION, "Welcome Admin " + user).show();
                        loggedUserName = adminDTO.getName();
                        loggedUserEmail = adminDTO.getEmail();
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/view/admin/dashboard_form.fxml"));
                            Scene scene1 = new Scene(root);
                            Stage stage1 = (Stage) login.getScene().getWindow();
                            stage1.setScene(scene1);
                            stage1.setTitle("Sign Up Form");
                            stage1.centerOnScreen();
                            isCredentialsOK = true;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
                if(isCredentialsOK==false){
                    new Alert(Alert.AlertType.ERROR, "Invalid Admin credentials..").show();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            try {
                boolean isCredentialsOK=false;
                List<UserDTO> allUsers = userBo.getAll();
                for(UserDTO userDTO:allUsers) {
                    if (userDTO.getName().equals(user) && userDTO.getPassword().equals(pass)) {
                        new Alert(Alert.AlertType.INFORMATION, "Welcome User " + user).show();
                        loggedUserEmail = userDTO.getEmail();
                        try {
                            email = userDTO.getEmail();
                            Parent root = FXMLLoader.load(getClass().getResource("/view/user/userDash_form.fxml"));
                            Scene scene1 = new Scene(root);
                            Stage stage1 = (Stage) login.getScene().getWindow();
                            stage1.setScene(scene1);
                            stage1.setTitle("Sign Up Form");
                            stage1.centerOnScreen();
                            isCredentialsOK=true;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
                if(isCredentialsOK==false){
                    new Alert(Alert.AlertType.ERROR, "Invalid User credentials..").show();
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

    public void imgViewOnAction(MouseEvent mouseEvent) {
        textview.setText(password.getText());
        password.setVisible(false);
        imgView.setVisible(false);
        textview.setVisible(true);
    }

    public void imgLockOnAction(MouseEvent mouseEvent) {
        password.setText(textview.getText());
        textview.setVisible(false);
        imgView.setVisible(true);
        password.setVisible(true);


    }

    public void txtForgetPasswordOnAction(MouseEvent mouseEvent) {

        if(!username.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Do you want to Change Your Password ?");
            alert.setContentText("Send OTP To Your Email");

            ButtonType buttonTypeYes = new ButtonType("OK");
            ButtonType buttonTypeNo = new ButtonType("Cancel");

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            alert.showAndWait().ifPresent(response -> {
                if (response == buttonTypeYes) {
                    try {
                        oneTimePassword = generateOTP();
                        Mail mail = new Mail();
                        mail.setMsg("Hello," + "\n\n\tAn OTP Request Detected at :  " + LocalDateTime.now() + " \n\n\tOTP : " + oneTimePassword + " \n\nThank You,\n" +
                                "FuelBee Support Team");
                        mail.setTo(username.getText());
                        tempUserName=username.getText();
                        mail.setSubject("OTP Verification");

                        Thread thread = new Thread(mail);
                        thread.start();

                        Parent load = FXMLLoader.load(getClass().getResource("/view/forgetpwd_form.fxml"));
                        Scene scene1 = new Scene(load);
                        Stage stage1 = (Stage) txtForgetPassword.getScene().getWindow();
                        stage1.setScene(scene1);
                        stage1.setTitle("Change Password Form");
                        stage1.centerOnScreen();

                        new SlideInUp(load).play();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else if (response == buttonTypeNo) {
                    System.out.println("User Canceled the Operation");
                }
            });
        }else{
            new Alert(Alert.AlertType.ERROR, "Empty Fields | Enter Valid User Name").show();
        }
    }
    public static int generateOTP(){
        Random random = new Random();
        int password = random.nextInt(9000000) + 1000000;
        System.out.println(password);
        return password;
    }
}
