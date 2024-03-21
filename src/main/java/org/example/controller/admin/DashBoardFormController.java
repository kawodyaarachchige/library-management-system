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
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Setter;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.BorrowingBO;
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;
import org.example.controller.LoginFormController;
import org.example.dto.BookDTO;
import org.example.entity.Book;
import org.example.entity.BorrowingBooks;
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

    @FXML
    private PieChart pieChart;
    @FXML private BarChart<String, Number> barChart;

    BookBO bookBo = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    BranchBO branchBo = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    UserBO userBo = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

   // BorrowingBO borrowingBo = (BorrowingBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BORROWING_BOOK);

   //BorrowingBO borrowingBo = (BorrowingBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BORROWING_BOOK);



    public void initialize() throws SQLException, ClassNotFoundException {

        loadData();
        try {
            populateBarChart();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
       // displayUserCountBarChart();

        lblWho.setText("Welcome" + " " + LoginFormController.loggedUserName);
        lblWho.setTextFill(Color.BLACK);
        lblWho.setFont(Font.font("Ubuntu", FontWeight.BOLD, 23));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateClock())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        updateClock();
        timeLabel.setTextFill(Color.BLACK);
        timeLabel.setText(": " + java.time.LocalTime.now());
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        dateLabel.setText(": " + java.time.LocalDate.now());
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        dateLabel.setTextFill(Color.BLACK);


    }



    private void updateClock() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = dateFormat.format(now);
        timeLabel.setText(formattedTime);
    }

    public void btnManageBookOnAction(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/admin/manageBook_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage) manageBook.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Manage Book Form");
            stage1.centerOnScreen();


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void btnManageBorrowingBookOnAction(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/admin/manageBorrowingBooks_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage) books.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Manage Borrowing Book Form");
            stage1.centerOnScreen();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void btnManageBranchesOnAction(MouseEvent mouseEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/admin/manageBranches_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage) branches.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Manage Branch Form");
            stage1.centerOnScreen();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void btnUserManageOnAction(MouseEvent mouseEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/admin/manageUser_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage) users.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Manage User Form");
            stage1.centerOnScreen();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage) logout.getScene().getWindow();
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

            String label2 = (String) result[2];
            Long count2 = (Long) result[3];
            String label3 = (String) result[4];
            Long count3 = (Long) result[5];

            pieChart.getData().add(new PieChart.Data(label1 + " (" + count1 + ")", count1));
            pieChart.getData().add(new PieChart.Data(label2 + " (" + count2 + ")", count2));
            pieChart.getData().add(new PieChart.Data(label3 + " (" + count3 + ")", count3));

        }
    }


    public void loadData() throws SQLException, ClassNotFoundException {

        List<Object[]> dataList = bookBo.getCounts();
        setDataToPieChart(dataList);
    }

    private void populateBarChart() throws SQLException {
        barChart.getData().clear();
        Map<String, Long> usersPerBranch = branchBo.getUsersPerBranch();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChart.setTitle("User Count per Branch");
        xAxis.setLabel("Branch");
        yAxis.setLabel("User Count");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Long> entry : usersPerBranch.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        barChart.getData().add(series);
    }

}

/*
    private void createBarChart() throws SQLException, ClassNotFoundException {
        for (int i = 1; i <= 12; i++) {
            String month = getMonthName(i);
            int borrowingCount = borrowingBo.getBorrowingCountByMonth(month);
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(month);
            series.getData().add(new XYChart.Data<>(month, borrowingCount));
            barChart.getData().add(series);
        }

        // Customize chart appearance
        yAxis.setLabel("Borrowing Count");
        barChart.setTitle("Books Borrowing Count by Month");
    }

    private String getMonthName(int monthNumber) {
        switch (monthNumber) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "";
        }
    }
*/








