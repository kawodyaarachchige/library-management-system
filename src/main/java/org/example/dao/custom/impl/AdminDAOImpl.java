package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.AdminDAO;
import org.example.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

    public class AdminDAOImpl implements AdminDAO {
        @Override
        public boolean save(Admin entity) throws SQLException, ClassNotFoundException {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            session.close();
            return true;

        }

        @Override
        public boolean update(Admin entity) throws SQLException, ClassNotFoundException {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            session.close();
            return true;
        }

        @Override
        public boolean delete(String id) throws SQLException, ClassNotFoundException {
            Admin search = search(id);
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.remove(search);
            transaction.commit();
            session.close();
            return true;
        }

        @Override
        public Admin search(String id) throws SQLException, ClassNotFoundException {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            Admin admin = session.get(Admin.class, id);
            transaction.commit();
            session.close();
            return admin;
        }

        @Override
        public List<Admin> getAll() throws SQLException, ClassNotFoundException {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            List<Admin> adminList = session.createQuery("FROM Admin").list();
            transaction.commit();
            session.close();
            return adminList;
        }

        @Override
        public String generateNextId() throws SQLException, ClassNotFoundException {
            return "AD001";

        }


        public boolean updatePassword(String username, String password) throws ClassNotFoundException {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "update Admin set password=:password where username=:username";
            Query query = session.createQuery(hql);
            query.setParameter("password", password);
            query.setParameter("username", username);
            int isUpdated = query.executeUpdate();
            transaction.commit();
            session.close();
            return isUpdated > 0;
        }
    }


