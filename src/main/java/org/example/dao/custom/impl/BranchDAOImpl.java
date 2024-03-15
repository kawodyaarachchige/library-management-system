package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.BranchDAO;
import org.example.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class BranchDAOImpl implements BranchDAO {
    @Override
    public boolean save(Branch ent) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ent);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Branch ent) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(ent);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Branch search = search(id);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(search);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Branch search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Branch branch = session.get(Branch.class, id);
        transaction.commit();
        session.close();
        return branch;
    }

    @Override
    public List<Branch> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Branch> branchList = session.createQuery("FROM Branch").list();
        transaction.commit();
        session.close();
        return branchList;
    }
    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object object = session.createQuery("SELECT id FROM Branch ORDER BY id DESC LIMIT 1").uniqueResult();
        transaction.commit();
        session.close();

        if(object != null) {
            String CurrentId = object.toString();
            String[] split = CurrentId.split("BR0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            if(id<10) {
                return "BR00" + id;
            } else {
                return "BR0" + id;
            }
        } else {
            return "BR001";
        }
    }

    @Override
    public Branch searchByLocation(String location) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Branch branch = (Branch) session.createQuery("FROM Branch WHERE location = :location").setParameter("location", location).uniqueResult();

        transaction.commit();
        session.close();
        return branch;
    }
}
