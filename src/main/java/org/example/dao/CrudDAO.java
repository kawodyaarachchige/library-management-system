package org.example.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO {
    boolean save(T entity) throws SQLException, ClassNotFoundException;
    boolean update(T entity) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    T search(String id) throws SQLException, ClassNotFoundException;
    List<T> getAll() throws SQLException, ClassNotFoundException;
    String generateNextId() throws SQLException, ClassNotFoundException;
}
