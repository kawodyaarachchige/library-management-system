package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.UserDAO;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User ent) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ent);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User ent) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(ent);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        User search = search(id);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(search);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return "US001";
    }
    @Override
    public List<User> searchUserByName(String name) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery("FROM User WHERE name = :name").setParameter("name", name).list();
        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public User searchUserByEmail(String email) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createQuery("FROM User WHERE email = :email").setParameter("email", email).uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public User searchUserByTelephone(String telephone) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createQuery("FROM User WHERE telephone = :telephone").setParameter("telephone", telephone).uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }
}
