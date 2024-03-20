package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.AdminDTO;
import org.example.entity.Admin;

import java.sql.SQLException;
import java.util.List;

public interface AdminBO extends SuperBO {
    boolean save(AdminDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(AdminDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;
    AdminDTO search(String id) throws SQLException, ClassNotFoundException;
    List<AdminDTO> getAll() throws SQLException, ClassNotFoundException;
    String generateNextId() throws SQLException, ClassNotFoundException;



    boolean updatePassword(String username, String password) throws ClassNotFoundException;
}
