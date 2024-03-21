package org.example.dao.custom;


import org.example.dao.CrudDAO;
import org.example.entity.BorrowingBooks;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface BorrowingDAO extends CrudDAO<BorrowingBooks> {

    List<BorrowingBooks> getPendingList() throws SQLException, ClassNotFoundException;
    List<BorrowingBooks> getUserList(String email) throws SQLException, ClassNotFoundException;
    List<BorrowingBooks> getNotReturnList(LocalDate date) throws SQLException, ClassNotFoundException;

   int getPendingBookCount() throws SQLException, ClassNotFoundException;
}

