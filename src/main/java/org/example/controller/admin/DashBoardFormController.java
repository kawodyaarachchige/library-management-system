package org.example.controller.admin;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookBO;
import org.example.controller.LoginFormController;
import org.example.dto.BookDTO;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class DashBoardFormController  {


    public AnchorPane manageBook;
    public AnchorPane books;
    public AnchorPane branches;
    public AnchorPane users;
    public Label back;
    public Label lblWho;
    public Label timeLabel;
    public Label dateLabel;
    public Button logout;
    @FXML
    private PieChart pieChart;



    BookBO bookBo = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);


    public void initialize() throws SQLException, ClassNotFoundException {

        loadData();

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



    }

       /* timeLabel.setTextFill(Color.BLACK);
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD,16));
        dateLabel.setText("Date: " + java.time.LocalDate.now());
       // lblWho.setText("Welcome Admin" + " " + );
*/

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

    private void setDataToPieChart(List<Object[]> dataList) throws SQLException, ClassNotFoundException {
        pieChart.getData().clear(); // Clear existing data
        for (Object[] result : dataList) {
            String label1 = (String) result[0];
            Long count1 = (Long) result[1];
            String color = "";
            String label2 = (String) result[2];
            String color2 = "";
            Long count2 = (Long) result[3];
            String label3 = (String) result[4];
            String color3 = "";
            Long count3 = (Long) result[5];

            switch (label1) {
                case "Books":
                    color = "#3366CC";
                    break;
                case "Branches":
                    color2 = "#DC3912";
                    break;
                case "Users":
                    color3 = "#FF9900";
                    break;
            }
            pieChart.getData().add(new PieChart.Data(label1 + " (" + count1 + ")", count1));
            pieChart.getData().add(new PieChart.Data(label2 + " (" + count2 + ")", count2));
            pieChart.getData().add(new PieChart.Data(label3 + " (" + count3 + ")", count3));

        }
    }
/*
   private void setDataToPieChart(List<Object[]> dataList) throws SQLException, ClassNotFoundException {
       pieChart.getData().clear(); // Clear existing data

       // Define colors for each label
       Map<String, String> labelColors = new HashMap<>();
       labelColors.put("Books", "#3366CC"); // Blue
       labelColors.put("Branches", "#DC3912"); // Red
       labelColors.put("Users", "#FF9900"); // Orange

       for (Object[] result : dataList) {
           String label = (String) result[0];
           Long count = (Long) result[1];



           // Get color for the label
           String color = labelColors.getOrDefault(label, "#000000"); // Default to black if no color found

           PieChart.Data data = new PieChart.Data(label + " (" + count + ")", count);
           data.getNode().setStyle("-fx-pie-color: " + color + ";"); // Set color for the slice
           pieChart.getData().add(data);
       }
   }
*/



    public void loadData() throws SQLException, ClassNotFoundException {

            List<Object[]> dataList = bookBo.getCounts();
            setDataToPieChart(dataList);
        }


    }







