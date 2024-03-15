package org.example.bo;


import org.example.bo.custom.impl.*;

public class BOFactory {
        private static org.example.bo.BOFactory boFactory;

        private BOFactory() {
        }

        public static org.example.bo.BOFactory getBoFactory() {
            return (boFactory == null) ? boFactory = new org.example.bo.BOFactory() : boFactory;
        }
        public enum BOTypes {
             ADMIN,BOOK,BRANCH,USER,BORROW,BORROWING_BOOK
        }

        public SuperBO getBO(BOTypes boTypes){
            switch (boTypes){
                case ADMIN:
                    return new AdminBOImpl();
                case BOOK:
                    return new BookBOImpl();
                case BRANCH:
                    return new BranchBOImpl();
                case USER:
                    return new UserBOImpl();
                case BORROW:
                    return new BorrowingBOImpl();
                case BORROWING_BOOK:
                    return new BorrowingBookBOImpl();
                default:
                    return null;
            }
        }



}