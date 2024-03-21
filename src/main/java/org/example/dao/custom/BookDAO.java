package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dto.BookDTO;
import org.example.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO extends CrudDAO<Book> {
    List<Book> searchByTitle(String title, String branch) throws SQLException, ClassNotFoundException;
    List<Book> searchByBranch(String branch) throws SQLException, ClassNotFoundException;

   List<Object[]> getCounts() throws SQLException, ClassNotFoundException;



}
