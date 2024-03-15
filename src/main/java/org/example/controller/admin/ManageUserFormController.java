package org.example.controller.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.bo.BOFactory;
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;
import org.example.dto.BranchDTO;
import org.example.dto.UserDTO;
import org.example.dto.tm.UserTM;

import java.sql.SQLException;
import java.util.List;

public class ManageUserFormController {
    public TextField txtSearch;
    public ComboBox<String> cmbSearchBy;
    public TextField txtMail;
    public TextField txtName;
    public TextField txtTelephone;
    public ComboBox <String> cmbBranch;
    public TableView <UserTM>tblUser;

    UserBO userBo = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    BranchBO branchBo = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    public void initialize() {
        setCellValueFactory();
        loadTables();
        loadComboBox();
    }
    private void setCellValueFactory() {
        tblUser.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblUser.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblUser.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("telephone"));
        tblUser.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("branch"));
    }

    private void loadTables() {
        tblUser.getItems().clear();
        try {
            List<UserDTO> all = userBo.getAll();
            for (UserDTO userDTO : all) {
                tblUser.getItems().add(new UserTM(userDTO.getEmail(), userDTO.getName(), userDTO.getTelephone(), userDTO.getBranch().getLocation()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadComboBox() {
        cmbBranch.getItems().clear();
        cmbSearchBy.getItems().clear();
        try {
            List<BranchDTO> all = branchBo.getAll();
            for (BranchDTO branchDTO : all) {
                cmbBranch.getItems().add(branchDTO.getLocation());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        cmbSearchBy.getItems().addAll("Name", "Email", "Telephone");
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        try{
            if (cmbSearchBy.getValue().equals("Name")) {
                List<UserDTO> userDTOS = userBo.searchUserByName(txtSearch.getText());
                tblUser.getItems().clear();
                for (UserDTO userDTO : userDTOS) {
                    tblUser.getItems().add(new UserTM(userDTO.getEmail(), userDTO.getName(), userDTO.getTelephone(), userDTO.getBranch().getLocation()));
                }
            } else if (cmbSearchBy.getValue().equals("Email")) {
                UserDTO userDTO = userBo.searchUserByEmail(txtSearch.getText());
                setToTable(userDTO);
            } else if (cmbSearchBy.getValue().equals("Telephone")) {
                UserDTO userDTO = userBo.searchUserByTelephone(txtSearch.getText());
                setToTable(userDTO);
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setToTable(UserDTO userDTO) {
        tblUser.getItems().clear();
        tblUser.getItems().add(new UserTM(userDTO.getEmail(), userDTO.getName(), userDTO.getTelephone(), userDTO.getBranch().getLocation()));
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        txtSearchOnAction(actionEvent);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String email = txtMail.getText();
        String username = txtName.getText();
        String telephone = txtTelephone.getText();
        String branch = cmbBranch.getValue();

        

        if (txtName.getText().isEmpty() || txtMail.getText().isEmpty() || txtTelephone.getText().isEmpty() || cmbBranch.getValue().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            try {
                BranchDTO branchDTO = branchBo.searchByLocation(cmbBranch.getValue());
                UserDTO search = userBo.search(txtMail.getText());
                userBo.update(new UserDTO(txtName.getText(),txtMail.getText(),search.getPassword(), Integer.parseInt(txtTelephone.getText()), branchDTO));
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                clearFields();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (txtName.getText().isEmpty() || txtMail.getText().isEmpty() || txtTelephone.getText().isEmpty() || cmbBranch.getValue().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            try {
                userBo.delete(txtMail.getText());
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                clearFields();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void tblOnAction(MouseEvent mouseEvent) {

        UserTM userTm = tblUser.getSelectionModel().getSelectedItem();
        txtName.setText(userTm.getName());
        txtMail.setText(userTm.getEmail());
        txtTelephone.setText(String.valueOf(userTm.getTelephone()));
        cmbBranch.setValue(userTm.getBranch());
    }
    private void clearFields() {
        txtMail.clear();
        txtName.clear();
        txtTelephone.clear();
        cmbBranch.setValue(null);
        initialize();
    }

}
