package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Admin;

import java.sql.SQLException;

public interface AdminDAO extends CrudDAO<Admin> {

    boolean updatePassword(String username, String password) throws ClassNotFoundException;

}
