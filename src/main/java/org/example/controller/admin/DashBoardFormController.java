package org.example.controller.admin;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.controller.LoginFormController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class DashBoardFormController {


    public AnchorPane manageBook;
    public AnchorPane books;
    public AnchorPane branches;
    public AnchorPane users;
    public Label back;
    public Label lblWho;
    public Label timeLabel;
    public Label dateLabel;
    public Button logout;


    public void initialize() {
        lblWho.setText("Hello" + " " + LoginFormController.loggedUserName);
        lblWho.setTextFill(Color.BLACK);
        lblWho.setFont(Font.font("Ubuntu", FontWeight.BOLD,20));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateClock())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        updateClock();
        timeLabel.setText(LocalDate.now().toString());
        timeLabel.setTextFill(Color.BLACK);
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD,16));
        dateLabel.setText("Date: " + java.time.LocalDate.now());
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD,16));
        dateLabel.setTextFill(Color.BLACK);


       /* timeLabel.setTextFill(Color.BLACK);
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD,16));
        dateLabel.setText("Date: " + java.time.LocalDate.now());
       // lblWho.setText("Welcome Admin" + " " + );
*/
    }
    private void updateClock() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = dateFormat.format(now);
        timeLabel.setText( formattedTime);
    }

    public void btnManageBookOnAction(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/admin/manageBook_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage)manageBook .getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Manage Book Form");
            stage1.centerOnScreen();


        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void btnManageBorrowingBookOnAction(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/admin/manageBorrowingBooks_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage)books.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Manage Borrowing Book Form");
            stage1.centerOnScreen();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void btnManageBranchesOnAction(MouseEvent mouseEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/admin/manageBranches_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage)branches .getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Manage Branch Form");
            stage1.centerOnScreen();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void btnUserManageOnAction(MouseEvent mouseEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/admin/manageUser_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage)users .getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Manage User Form");
            stage1.centerOnScreen();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage)logout .getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Login Form");
            stage1.centerOnScreen();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
