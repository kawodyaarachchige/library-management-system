package org.example.controller.user;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookBO;
import org.example.controller.LoginFormController;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.example.controller.LoginFormController.email;

public class UserDashFormController {
    public AnchorPane viewBook;
    public AnchorPane borrowBook;
    public AnchorPane setting;
    public Button logout;
    public Label back;
    public Label lblTime;
    public Label lblDate;
    public Label lblWho;
    public PieChart pieChart;

    BookBO bookBo = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);


    public void initialize() throws SQLException, ClassNotFoundException {
       /* lblWho.setText("Hello" + "" + email);
        lblWho.setTextFill(Color.BLACK);
        lblWho.setFont(Font.font("Ubuntu", FontWeight.BOLD,20));*/

        loadData();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateClock())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        updateClock();
        lblTime.setText(LocalDate.now().toString());
        lblTime.setTextFill(Color.BLACK);
        lblTime.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        lblDate.setText(LocalDate.now().toString());
        lblDate.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        lblDate.setTextFill(Color.BLACK);
    }

    private void updateClock() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = dateFormat.format(now);
        lblTime.setText(formattedTime);
    }

    public void btnBookFormOnAction(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/user/book_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage) viewBook.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Book Form");
            stage1.centerOnScreen();

            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/book_form.fxml"));
            Parent root = loader.load();
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage) viewBook.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Book Form");
            stage1.centerOnScreen();

            BookFormController user = loader.getController();
            user.txtUserMail.setText(email);
            user.start();*/

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void btnSettingOnAction(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/user/setting_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage) setting.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Setting Form");
            stage1.centerOnScreen();


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void btnBorrowBookOnAction(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/user/borrowingBooks_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage) borrowBook.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Borrow Book Form");
            stage1.centerOnScreen();


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void lablBackOnAction(MouseEvent mouseEvent) {
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Scene scene1 = new Scene(load);
            Stage stage1 = (Stage) back.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("DashBoard Form");
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

    private void loadData() throws SQLException, ClassNotFoundException {
        List<Object[]> dataList = bookBo.getBookCountsByTitle();
        setDataToPieChart(dataList);
    }

    private void setDataToPieChart(List<Object[]> dataList) {
        pieChart.getData().clear(); // Clear existing data
        // Format data for Pie Chart
        for (Object[] result : dataList) {
            String genre = (String) result[0];
            Long count = (Long) result[1];
            pieChart.getData().add(new PieChart.Data(genre, count));
        }
    }

}
