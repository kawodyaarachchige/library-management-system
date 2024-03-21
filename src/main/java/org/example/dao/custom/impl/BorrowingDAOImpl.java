package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.BorrowingDAO;
import org.example.entity.BorrowingBooks;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BorrowingDAOImpl implements BorrowingDAO {

    @Override
    public List<BorrowingBooks> getPendingList() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<BorrowingBooks> list = session.createQuery("FROM BorrowingBooks WHERE status='pending'").list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<BorrowingBooks> getUserList(String email) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<BorrowingBooks> list = session.createQuery("FROM BorrowingBooks WHERE user.email='" + email + "'").list();
        transaction.commit();
        session.close();
        return list;

    }

    @Override
    public List<BorrowingBooks> getNotReturnList(LocalDate date) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<BorrowingBooks> list = session.createQuery("FROM BorrowingBooks WHERE returnDate <= :date and status='pending'").setParameter("date", date).list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public int getPendingBookCount() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<BorrowingBooks> list = session.createQuery("SELECT count (*) FROM BorrowingBooks WHERE status='pending'").list();
        transaction.commit();
        session.close();
        return list.size();
    }


    @Override
    public boolean save(BorrowingBooks entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(BorrowingBooks entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        BorrowingBooks search = search(id);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(search);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public BorrowingBooks search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        BorrowingBooks borrowingBooks = session.get(BorrowingBooks.class, id);
        transaction.commit();
        session.close();
        return borrowingBooks;
    }

    @Override
    public List<BorrowingBooks> getAll() throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<BorrowingBooks> list = session.createQuery("FROM BorrowingBooks").list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object object = session.createQuery("SELECT id FROM BorrowingBooks ORDER BY id DESC LIMIT 1").uniqueResult();
        transaction.commit();
        session.close();

        if (object != null) {
            String CurrentId = object.toString();
            String[] split = CurrentId.split("BRA");

            int id = Integer.parseInt(split[1]); //01
            id++;
            if (id < 10) {
                return "BRA00" + id;
            } else {
                return "BRA0" + id;
            }
        } else {
            return "BRA001";
        }


    }
    /*@Override
    public List<BorrowingBooks> getBorrowingBooksByMonth(String month) throws SQLException, ClassNotFoundException {
     *//*   Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<BorrowingBooks> list = session.createQuery("FROM BorrowingBooks WHERE MONTH(borrowDate) = :month").setParameter("month", month).list();
        transaction.commit();
        session.close();
        return list;
    }*/

}
