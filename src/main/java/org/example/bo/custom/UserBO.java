package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    boolean save(UserDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(UserDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;
    UserDTO search(String id) throws SQLException, ClassNotFoundException;
    List<UserDTO> getAll() throws SQLException, ClassNotFoundException;
    String generateNextId() throws SQLException, ClassNotFoundException;
    List<UserDTO> searchUserByName(String name) throws SQLException, ClassNotFoundException;
    UserDTO searchUserByEmail(String email) throws SQLException, ClassNotFoundException;
    UserDTO searchUserByTelephone(String telephone) throws SQLException, ClassNotFoundException;
}
