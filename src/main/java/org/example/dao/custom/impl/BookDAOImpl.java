package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.BookDAO;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public boolean save(Book entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Book entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Book search = search(id);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(search);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Book search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Book book = session.get(Book.class, id);
        transaction.commit();
        session.close();
        return book;
    }

    @Override
    public List<Book> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Book> books = session.createQuery("FROM Book").list();
        transaction.commit();
        session.close();
        return books;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object object = session.createQuery("SELECT id FROM Book ORDER BY id DESC LIMIT 1").uniqueResult();
        transaction.commit();
        session.close();

        if(object != null) {
            String CurrentId = object.toString();
            String[] split = CurrentId.split("B0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            if(id<10) {
                return "B00" + id;
            } else {
                return "B0" + id;
            }
        } else {
            return "B001";
        }
    }

    @Override
    public List<Book> searchByTitle(String title, String branch) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Book> book = session.createQuery("FROM Book WHERE branch.id = :branch AND title = :title", Book.class)
                .setParameter("branch", branch).setParameter("title", title).list();
        transaction.commit();
        session.close();

        return book;
    }

    @Override
    public List<Book> searchByBranch(String branch) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Book> book = session.createQuery("FROM Book WHERE branch.id = :branch and status = 'available'", Book.class)
                .setParameter("branch", branch).list();
        transaction.commit();
        session.close();

        return book;
    }
}
