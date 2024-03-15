package org.example.controller.admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class DashBoardFormController {


    public AnchorPane manageBook;
    public AnchorPane books;
    public AnchorPane branches;
    public AnchorPane users;

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
}
