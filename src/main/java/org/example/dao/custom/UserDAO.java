package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    List<User> searchUserByName(String name) throws SQLException, ClassNotFoundException;
    User searchUserByEmail(String email) throws SQLException, ClassNotFoundException;
    User searchUserByTelephone(String telephone) throws SQLException, ClassNotFoundException;
}
