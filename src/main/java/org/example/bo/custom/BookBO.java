package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.BookDTO;

import java.sql.SQLException;
import java.util.List;

public interface BookBO extends SuperBO {
    boolean save(BookDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(BookDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;
    BookDTO search(String id) throws SQLException, ClassNotFoundException;
    List<BookDTO> getAll() throws SQLException, ClassNotFoundException;
    String generateNextId() throws SQLException, ClassNotFoundException;
    List<BookDTO> searchByTitle(String title, String branch) throws SQLException, ClassNotFoundException;
    List<BookDTO> searchByBranch(String branch) throws SQLException, ClassNotFoundException;


}
