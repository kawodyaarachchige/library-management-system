package org.example.dao;

import org.example.dao.custom.impl.*;

public class DAOFactory {

        private static org.example.dao.DAOFactory daoFactory;

        private DAOFactory() {

        }

        public static org.example.dao.DAOFactory getDaoFactory() {
            return daoFactory == null ? daoFactory = new org.example.dao.DAOFactory() : daoFactory;
        }

        public enum DAOTypes {
            ADMIN, BOOK, BRANCH, USER,BORROWING_BOOK
        }
        public SuperDAO getDAO(DAOTypes daoTypes){
            switch (daoTypes){
                case ADMIN:
                    return new AdminDAOImpl();
                case BOOK:
                    return new BookDAOImpl();
                case BRANCH:
                    return new BranchDAOImpl();
                case BORROWING_BOOK:
                    return new BorrowingDAOImpl();
                case USER:
                    return new UserDAOImpl();
                default:
                    return null;
            }
        }
    }

