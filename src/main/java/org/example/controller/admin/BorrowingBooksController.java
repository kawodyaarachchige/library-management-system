package org.example.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.BorrowingBO;
import org.example.bo.custom.BorrowingBooksBO;
import org.example.dto.BookDTO;
import org.example.dto.BorrowingBookDTO;
import org.example.dto.tm.BorrowBookTM;
import org.example.dto.tm.BorrowTM;
import org.example.validation.Mail;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.example.controller.LoginFormController.email;


public class BorrowingBooksController {
    public TableView <BorrowTM> tblBorrow;
    public TextField txtBorrowId;
    public TextField txtUserMail;
    public TextField txtBookId;
    public ComboBox <String> cmbStatus;
    public TextField txtEmail;
    public AnchorPane borrowingBook;
    public Label back;

    BorrowingBO borrowingBO = (BorrowingBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BORROW);
    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    public void initialize() {
        loadBorrowTable();
        setCellValueFactory();
        setCmbValues();
    }
    private void setCmbValues() {
        cmbStatus.getItems().clear();
        cmbStatus.getItems().add("Pending");
        cmbStatus.getItems().add("Returned");
    }
    private void loadBorrowTable() {
        try {
            tblBorrow.getItems().clear();
            List<BorrowingBookDTO> all = borrowingBO.getAll();

            for (BorrowingBookDTO borrowDTO : all) {
                tblBorrow.getItems().add(new BorrowTM(borrowDTO.getId(), borrowDTO.getUser().getName(), borrowDTO.getBook().getId()+"-"+borrowDTO.getBook().getTitle(), borrowDTO.getBorrowDate(), borrowDTO.getReturnDate(), borrowDTO.getStatus()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        if (txtBorrowId.getText().isEmpty() || txtUserMail.getText().isEmpty() || txtBookId.getText().isEmpty() || cmbStatus.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Missing Information").show();
        } else {
            String id = txtBorrowId.getText();
            String status = cmbStatus.getValue();
            try {
                BorrowingBookDTO search = borrowingBO.search(id);
                BorrowingBookDTO borrowDTO = new BorrowingBookDTO(search.getId(), search.getUser(), search.getBook(), search.getBorrowDate(), search.getReturnDate(), status);
                BookDTO book = bookBO.search(search.getBook().getId());
                book.setStatus("Available");
                borrowingBO.update(borrowDTO);
                bookBO.update(book);
                new Alert(Alert.AlertType.CONFIRMATION, "Updated successfully!").show();
                initialize();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void serachOnAction(ActionEvent actionEvent) {
        String mail = txtEmail.getText();
        try {
            tblBorrow.getItems().clear();
            List<BorrowingBookDTO> all = borrowingBO.getUserList(mail);

            for (BorrowingBookDTO borrowDTO : all) {
                tblBorrow.getItems().add(new BorrowTM(borrowDTO.getId(), borrowDTO.getUser().getName(), borrowDTO.getBook().getId()+"-"+borrowDTO.getBook().getTitle(), borrowDTO.getBorrowDate(), borrowDTO.getReturnDate(), borrowDTO.getStatus()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void AllOnAction(MouseEvent mouseEvent) {
        initialize();
    }

    public void notReturnedOnAction(MouseEvent mouseEvent) {
        LocalDate today = LocalDate.now();
        try {
            tblBorrow.getItems().clear();
            List<BorrowingBookDTO> all = borrowingBO.getNotReturnList(today);

            for (BorrowingBookDTO borrowDTO : all) {
                tblBorrow.getItems().add(new BorrowTM(borrowDTO.getId(), borrowDTO.getUser().getName(), borrowDTO.getBook().getId()+"-"+borrowDTO.getBook().getTitle(), borrowDTO.getBorrowDate(), borrowDTO.getReturnDate(), borrowDTO.getStatus()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void pendingOnAction(MouseEvent mouseEvent) {
        try {
            tblBorrow.getItems().clear();
            List<BorrowingBookDTO> all = borrowingBO.getPendingList();

            for (BorrowingBookDTO borrowingDTO : all) {
                tblBorrow.getItems().add(new BorrowTM(borrowingDTO.getId(), borrowingDTO.getUser().getName(), borrowingDTO.getBook().getId()+"-"+borrowingDTO.getBook().getTitle(), borrowingDTO.getBorrowDate(), borrowingDTO.getReturnDate(), borrowingDTO.getStatus()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void tblOnAction(MouseEvent mouseEvent) {
        BorrowTM tm = tblBorrow.getSelectionModel().getSelectedItem();
        try {
            BorrowingBookDTO search = borrowingBO.search(tm.getId());
            txtBorrowId.setText(search.getId());
            txtUserMail.setText(search.getUser().getEmail());
            txtBookId.setText(search.getBook().getId());
            cmbStatus.setValue(search.getStatus());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void lablBackOnAction(MouseEvent mouseEvent) {
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/admin/dashboard_form.fxml"));
            Scene scene1 = new Scene(load);
            Stage stage1 = (Stage) back.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("DashBoard Form");
            stage1.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void setCellValueFactory() {
        tblBorrow.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBorrow.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("user"));
        tblBorrow.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("book"));
        tblBorrow.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        tblBorrow.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        tblBorrow.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void btninnformOnAction(ActionEvent actionEvent) {
      try{
        Mail mail = new Mail();
        mail.setMsg("Hello User! \n\n Please return all borrowed books promptly to the library to ensure their availability for other readers. \n\nThank you for your cooperation.");
        mail.setTo(txtUserMail.getText());
        mail.setSubject("OTP Verification");
        Thread thread = new Thread (mail);
        thread.start();
        new Alert(Alert.AlertType.CONFIRMATION, "Mail Sent Successfully").show();
    }catch (Exception e){
        new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
    }
    }

}
