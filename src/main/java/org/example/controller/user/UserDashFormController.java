package org.example.controller.user;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserDashFormController {
    public AnchorPane viewBook;
    public AnchorPane borrowBook;
    public AnchorPane setting;


    public void btnBookFormOnAction(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/user/book_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage)viewBook .getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Book Form");
            stage1.centerOnScreen();


        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void btnSettingOnAction(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/user/setting_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage)setting .getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Setting Form");
            stage1.centerOnScreen();


        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void btnBorrowBookOnAction(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/user/borrowingBooks_form.fxml"));
            Scene scene1 = new Scene(root);
            Stage stage1 = (Stage)borrowBook .getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Borrow Book Form");
            stage1.centerOnScreen();


        }catch (Exception e){
            System.out.println(e);
        }

    }
}
