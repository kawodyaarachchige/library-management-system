package org.example.controller.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.BorrowingBO;
import org.example.bo.custom.BorrowingBooksBO;
import org.example.dto.BookDTO;
import org.example.dto.BorrowingBookDTO;
import org.example.dto.tm.BorrowBookTM;
import org.example.dto.tm.BorrowTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class BorrowingBooksController {
    public TableView <BorrowTM> tblBorrow;
    public TextField txtBorrowId;
    public TextField txtUserMail;
    public TextField txtBookId;
    public ComboBox <String> cmbStatus;
    public TextField txtEmail;
    public AnchorPane borrowingBook;

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
    private void setCellValueFactory (){}
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

}
