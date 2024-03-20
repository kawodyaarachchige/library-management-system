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
import org.example.bo.custom.BranchBO;
import org.example.dto.BranchDTO;
import org.example.dto.tm.BranchTM;

import java.sql.SQLException;
import java.util.List;

public class ManageBranchesFormController {
    public TextField txtLocation;
    public TextField txtEmail;
    public ComboBox<String> cmbBranch;
    public TableView<BranchTM> tblBranch;
    public TextField txtId;
    public TextField txtTelephone;
    public TextField txtAddress;
    public AnchorPane manageBranch;
    public Label back;

    BranchBO branchBoImpl = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    public void initialize() {
        setNextId();
        loadComboBox();
        loadtable();
        setCellValueFactory();
    }

    private void loadtable() {
        tblBranch.getItems().clear();
        try {
            List<BranchDTO> all = branchBoImpl.getAll();
            for (BranchDTO branchDTO : all) {
                tblBranch.getItems().add(new BranchTM(branchDTO.getId(), branchDTO.getLocation(), branchDTO.getTelephone(), branchDTO.getEmail()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        tblBranch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBranch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("location"));
        tblBranch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("telephone"));
        tblBranch.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadComboBox() {
        cmbBranch.getItems().clear();
        try {
            List<BranchDTO> all = branchBoImpl.getAll();
            for (BranchDTO branchDTO : all) {
                cmbBranch.getItems().add(branchDTO.getLocation());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setNextId() {
        try {
            String nextId = branchBoImpl.generateNextId();
            txtId.setText(nextId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtLocationOnAction(ActionEvent actionEvent) {
        try {
            BranchDTO branchDTO = branchBoImpl.searchByLocation(txtLocation.getText());
            if (branchDTO != null) {
                txtId.setText(branchDTO.getId());
                txtTelephone.setText(String.valueOf(branchDTO.getTelephone()));
                txtEmail.setText(branchDTO.getEmail());
                txtAddress.setText(branchDTO.getAddress());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String location = txtLocation.getText();
        String telephone = txtTelephone.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();

        if (id.isEmpty() || location.isEmpty() || telephone.isEmpty() || email.isEmpty() || address.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            try {
                branchBoImpl.save(new BranchDTO(id, location, Integer.parseInt(telephone), email, address));
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                initialize();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String location = txtLocation.getText();
        String telephone = txtTelephone.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();

        if (id.isEmpty() || location.isEmpty() || telephone.isEmpty() || email.isEmpty() || address.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            try {
                branchBoImpl.update(new BranchDTO(id, location, Integer.parseInt(telephone), email, address));
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                initialize();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        if (id.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter branch id").show();
        } else {
            try {
                branchBoImpl.delete(id);
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                initialize();
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                throw new RuntimeException(e);
            }
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        setNextId();
        txtLocation.clear();
        txtTelephone.clear();
        txtEmail.clear();
        txtAddress.clear();
    }


    public void txtIdOnAction(ActionEvent actionEvent) {
       /* try {
            BranchDTO branchDTO = branchBoImpl.search(txtId.getText());
            if (branchDTO != null) {
                txtLocation.setText(branchDTO.getLocation());
                txtTelephone.setText(String.valueOf(branchDTO.getTelephone()));
                txtEmail.setText(branchDTO.getEmail());
                txtAddress.setText(branchDTO.getAddress());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
        if (txtId.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter branch id").show();
        } else {
            try {
                BranchDTO branchDTO = branchBoImpl.search(txtId.getText());
                txtLocation.setText(branchDTO.getLocation());
                txtTelephone.setText(String.valueOf(branchDTO.getTelephone()));
                txtEmail.setText(branchDTO.getEmail());
                txtAddress.setText(branchDTO.getAddress());
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void cmbBranchOnAction(ActionEvent actionEvent) {
        try {
            BranchDTO branchDTO = branchBoImpl.searchByLocation(cmbBranch.getValue());
            if (branchDTO != null) {
                txtId.setText(branchDTO.getId());
                txtLocation.setText(branchDTO.getLocation());
                txtTelephone.setText(String.valueOf(branchDTO.getTelephone()));
                txtEmail.setText(branchDTO.getEmail());
                txtAddress.setText(branchDTO.getAddress());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void tblOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        BranchTM branchTm = tblBranch.getSelectionModel().getSelectedItem();
        txtId.setText(branchTm.getId());

        BranchDTO search = branchBoImpl.search(branchTm.getId());
        txtLocation.setText(search.getLocation());
        txtTelephone.setText(String.valueOf(branchTm.getTelephone()));
        txtEmail.setText(branchTm.getEmail());
        txtAddress.setText(search.getAddress());
    }

    public void lblBackOnAction(MouseEvent mouseEvent) {
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/admin/dashboard_form.fxml"));
            Scene scene1 = new Scene(load);
            Stage stage1 = (Stage) back.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Admin Dashboard");
            stage1.centerOnScreen();
        }
        catch (Exception e){
            System.out.println(e);
        }}

}