package org.example.controller.user;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lombok.Setter;
import org.example.bo.BOFactory;
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;
import org.example.dto.UserDTO;

import java.sql.SQLException;

public class SettingFormController {
    public TextField txtEmail;
    public TextField txtCurrentPW;
    public TextField txtNewPW;
    public TextField txtREPW;
    public TextField txtUsername;
    public TextField txtTelephone;
    public ComboBox <String>cmbBranch;
    @Setter
    private String email;

    public UserDTO userdto;

    BranchBO branchBOImpl = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);
    UserBO userBOImpl = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() throws ClassNotFoundException {
        txtEmail.setEditable(false);
        initializeUser();
        cmbBranch.setEditable(false);
    }

    public void initializeUser() {
        try {
            branchBOImpl.getAll().forEach(branch -> cmbBranch.getItems().add(branch.getLocation()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String email = txtEmail.getText();
        String username = txtUsername.getText();
        String telephone = txtTelephone.getText();
        String branch = cmbBranch.getValue();

        try {
            userdto = userBOImpl.search(email);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (username.isEmpty() || branch.isEmpty() || telephone.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            try {
                userBOImpl.update(new UserDTO(username, email, userdto.getPassword(), Integer.parseInt(telephone), userdto.getBranch()));
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String email = txtEmail.getText();
        String currentPw = txtCurrentPW.getText();
        String newPw = txtNewPW.getText();
        String newPw2 = txtREPW.getText();

        try {
            userdto = userBOImpl.search(email);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (currentPw.isEmpty() || newPw.isEmpty() || newPw2.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else{
            if (!userdto.getPassword().equals(currentPw)) {
                new Alert(Alert.AlertType.ERROR, "Invalid password").show();
            } else if (!newPw.equals(newPw2)) {
                new Alert(Alert.AlertType.ERROR, "Password does not match").show();
            } else {
                try {
                    userBOImpl.save(new UserDTO(userdto.getName(), email, newPw, userdto.getTelephone(), userdto.getBranch()));
                    new Alert(Alert.AlertType.CONFIRMATION, "Changed Password Successfully").show();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
