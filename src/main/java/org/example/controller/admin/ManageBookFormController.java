package org.example.controller.admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.BranchBO;
import org.example.dto.BookDTO;
import org.example.dto.BranchDTO;
import org.example.dto.tm.BookTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManageBookFormController {
    public TextField txtId;
    public TextField txtGenre;
    public TextField txtAuthor;
    public JFXComboBox<String> cmbStatus;
    public JFXComboBox <String> cmbBranches;
  /*  public TableColumn columnBookId;
    public TableColumn columnTitle;
    public TableColumn columnAuthor;
    public TableColumn columnStatus;*/
    public ComboBox <String>cmbBran;


    public TextField txtTitle;
    public TableView <BookTM>tblBook;
    public Label back;
    public JFXTextField txtSearch;

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    public void initialize() {
        setNextId();
        loadComboBox();
        loadtable();
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        tblBook.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBook.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tblBook.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tblBook.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    private void loadComboBox() {
        cmbBranches.getItems().clear();
        cmbBran.getItems().clear();
        try {
            List<BranchDTO> all = branchBO.getAll();
            for (BranchDTO branchDTO : all) {
                cmbBranches.getItems().add(branchDTO.getLocation());
                cmbBran.getItems().add(branchDTO.getLocation());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        cmbStatus.getItems().addAll("Available", "Not Available");
    }

    private void loadtable() {
        tblBook.getItems().clear();
        try {
            List<BookDTO> all = bookBO.getAll();
            for (BookDTO bookDTO : all) {
                tblBook.getItems().add(new BookTM(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getStatus()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    clear();
    }

    private void setNextId() {
        try {
            String nextId = bookBO.generateNextId();
            txtId.setText(nextId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        String status = cmbStatus.getValue();
        String branch = cmbBran.getValue();


        if (id.isEmpty() || title.isEmpty() || author.isEmpty() || genre.isEmpty() || status.isEmpty() || branch.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            try {
                BranchDTO branchDTO = branchBO.searchByLocation(branch);
                bookBO.update(new BookDTO(id, author, genre, status,title,branchDTO));
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                initialize();
                clear();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            bookBO.delete(id);
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            initialize();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        String status = cmbStatus.getValue();
        String branch = cmbBranches.getValue();


        if (id.isEmpty() || title.isEmpty() || author.isEmpty() || genre.isEmpty() || status.isEmpty() || branch.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            try {
                BranchDTO branchDTO = branchBO.searchByLocation(branch);
                bookBO.save(new BookDTO(id, title, author, genre, status, branchDTO));
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                initialize();
                clear();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        }
    public void btnSearchOnAction(ActionEvent actionEvent) {
        searchBook();
    }

    private void searchBook() {
        /*System.out.println("searching");
        String title = txtSearch.getText();
        String branch = cmbBran.getValue();

        //Load all books in the library
        try {
            String[] arr = branch.split(" - ");
            String branchID = arr[0];

            BranchDTO dto = branchBO.searchByLocation(branchID);
            List<BookDTO> all = bookBO.searchByTitle(title, dto.getId());
            List<BookDTO> allBooksInLibraries = bookBO.getAll();
            for(BookDTO bookDTO : allBooksInLibraries){
                if(bookDTO.getTitle().equals(title)){
                    txtTitle.setText(bookDTO.getTitle());
                    txtAuthor.setText(bookDTO.getAuthor());
                    txtId.setText(bookDTO.getId());
                    txtGenre.setText(bookDTO.getGenre());
                }
            }

            tblBook.getItems().clear();
            for (BookDTO bookDTO : all) {
                tblBook.getItems().add(new BookTM(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getStatus()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
   /*     try{
            BookDTO search = bookBO.search(txtSearch.getText());
            if (search == null) {
                new Alert(Alert.AlertType.ERROR, "Book Not Found").show();
            }else {
                txtTitle.setText(search.getTitle());
                txtAuthor.setText(search.getAuthor());
                txtId.setText(search.getId());
                txtGenre.setText(search.getGenre());
                *//*cmbStatus.setValue(search.getStatus());
                cmbBranches.setValue(search.getBranch().getLocation());*//*
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
*/  String title = txtSearch.getText();
        String branch = cmbBran.getValue();

        try {
            BranchDTO dto = branchBO.searchByLocation(branch);
            List<BookDTO> all = bookBO.searchByTitle(title, dto.getId());
            tblBook.getItems().clear();
            for (BookDTO bookDTO : all) {
                tblBook.getItems().add(new BookTM(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getStatus()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public void btnBackOnAction(MouseEvent mouseEvent) {
    try {
        Parent load = FXMLLoader.load(getClass().getResource("/view/admin/dashboard_form.fxml"));
        Scene scene1 = new Scene(load);
        Stage stage1 = (Stage)back .getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Login Form");
        stage1.centerOnScreen();

    }catch (IOException e){
    e.printStackTrace();
}
    }
    public void clear(){
        txtId.clear();
        txtTitle.clear();
        txtAuthor.clear();
        txtGenre.clear();
        cmbStatus.setValue(null);
        initialize();
    }

}
