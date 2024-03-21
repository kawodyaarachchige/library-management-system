package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Branch;

import java.sql.SQLException;
import java.util.Map;

public interface BranchDAO extends CrudDAO<Branch> {
    Branch searchByLocation(String location) throws SQLException, ClassNotFoundException;

    Map<String, Long> getUsersPerBranch()  throws SQLException, ClassNotFoundException;
}
