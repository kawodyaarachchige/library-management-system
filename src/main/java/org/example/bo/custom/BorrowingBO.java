package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.BorrowingBookDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface BorrowingBO extends SuperBO {
    boolean save(BorrowingBookDTO dto) throws SQLException, ClassNotFoundException;
    boolean update(BorrowingBookDTO dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    BorrowingBookDTO search(String id) throws SQLException, ClassNotFoundException;
    List<BorrowingBookDTO> getAll() throws SQLException, ClassNotFoundException;
    String generateNextId() throws SQLException, ClassNotFoundException;
    List<BorrowingBookDTO> getPendingList() throws SQLException, ClassNotFoundException;
    List<BorrowingBookDTO> getUserList(String email) throws SQLException, ClassNotFoundException;
    List<BorrowingBookDTO> getNotReturnList(LocalDate date) throws SQLException, ClassNotFoundException;
}
