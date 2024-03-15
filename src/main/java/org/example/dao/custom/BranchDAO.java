package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Branch;

import java.sql.SQLException;

public interface BranchDAO extends CrudDAO<Branch> {
    Branch searchByLocation(String location) throws SQLException, ClassNotFoundException;
}
