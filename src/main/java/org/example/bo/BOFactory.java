package org.example.bo;


import org.example.bo.custom.impl.AdminBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }
    public enum BOTypes {
        ADMIN,BOOK,BRANCH,USER;
    }

    public SuperBO getBO(BOTypes boTypes) {
        /*switch (boTypes){
            case ADMIN:
                return new AdminBOImpl();
            case BOOK:
                return new BookBOImpl();
            case BRANCH:
                return new BranchBOImpl();
            case USER:
                return new UserBOImpl();
                default:
                return null;
        }*/
        return null;
    }
}