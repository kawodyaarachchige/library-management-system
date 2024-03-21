package org.example.bo.custom.impl;

import org.example.bo.custom.BorrowingBooksBO;
import org.example.config.FactoryConfiguration;
import org.example.dto.BookDTO;
import org.example.dto.BorrowingBookDTO;
import org.example.entity.Book;
import org.example.entity.BorrowingBooks;
import org.example.entity.Branch;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BorrowingBookBOImpl implements BorrowingBooksBO {
    public void borrowBook(BorrowingBookDTO borrowDTO, BookDTO book) {
        Session session = null;
        try {
            User user = new User(borrowDTO.getUser().getName(), borrowDTO.getUser().getEmail(), borrowDTO.getUser().getPassword(), borrowDTO.getUser().getTelephone(), null, null);
            Branch branch = new Branch(book.getBranch().getId(), book.getBranch().getLocation(), book.getBranch().getTelephone(), book.getBranch().getEmail(), book.getBranch().getAddress(), null, null);
            Book bookEnt = new Book(borrowDTO.getBook().getId(), borrowDTO.getBook().getTitle(), borrowDTO.getBook().getAuthor(), borrowDTO.getBook().getGenre(), borrowDTO.getBook().getStatus(), branch, null);
            BorrowingBooks borrowBooks = new BorrowingBooks(borrowDTO.getId(), user, bookEnt, borrowDTO.getBorrowDate(), borrowDTO.getReturnDate(), borrowDTO.getStatus());

            bookEnt.setStatus("Not Available");

            session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            session.persist(borrowBooks);
            session.update(bookEnt);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}



