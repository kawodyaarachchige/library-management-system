package org.example.bo.custom.impl;

import org.example.bo.SuperBO;
import org.example.bo.custom.BorrowingBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.BorrowingDAO;
import org.example.dto.BookDTO;
import org.example.dto.BorrowingBookDTO;
import org.example.dto.UserDTO;
import org.example.entity.Book;
import org.example.entity.BorrowingBooks;
import org.example.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowingBOImpl implements BorrowingBO {

    BorrowingDAO borrowingDaoImpl = (BorrowingDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BORROWING_BOOK);
    @Override
    public boolean save(BorrowingBookDTO dto) throws SQLException, ClassNotFoundException {
        User user = new User(dto.getUser().getName(), dto.getUser().getEmail(), dto.getUser().getPassword(), dto.getUser().getTelephone(), null, null);
        Book book = new Book(dto.getBook().getId(), dto.getBook().getTitle(), dto.getBook().getAuthor(), dto.getBook().getGenre(), dto.getBook().getStatus(), null, null);
        return borrowingDaoImpl.save(new BorrowingBooks(dto.getId(),user,book,dto.getBorrowDate(),dto.getReturnDate(),dto.getStatus()));
    }

    @Override
    public boolean update(BorrowingBookDTO dto) throws SQLException, ClassNotFoundException {
        User user = new User(dto.getUser().getName(), dto.getUser().getEmail(), dto.getUser().getPassword(), dto.getUser().getTelephone(), null, null);
        Book book = new Book(dto.getBook().getId(), dto.getBook().getTitle(), dto.getBook().getAuthor(), dto.getBook().getGenre(), dto.getBook().getStatus(), null, null);
        return borrowingDaoImpl.update(new BorrowingBooks(dto.getId(),user,book,dto.getBorrowDate(),dto.getReturnDate(),dto.getStatus()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return borrowingDaoImpl.delete(id);
    }

    @Override
    public BorrowingBookDTO search(String id) throws SQLException, ClassNotFoundException {
        BorrowingBooks search = borrowingDaoImpl.search(id);
        UserDTO user = new UserDTO(search.getUser().getName(), search.getUser().getEmail(), search.getUser().getPassword(), search.getUser().getTelephone(), null);
        BookDTO book = new BookDTO(search.getBook().getId(), search.getBook().getTitle(), search.getBook().getAuthor(), search.getBook().getGenre(), search.getBook().getStatus(), null);
        return new BorrowingBookDTO(search.getId(),user,book,search.getBorrowDate(),search.getReturnDate(),search.getStatus());
    }

    @Override
    public List<BorrowingBookDTO> getAll() throws SQLException, ClassNotFoundException {
        List<BorrowingBooks> all = borrowingDaoImpl.getAll();
        List<BorrowingBookDTO> list = new ArrayList<>();

        for(BorrowingBooks b : all){
            UserDTO user = new UserDTO(b.getUser().getName(), b.getUser().getEmail(), b.getUser().getPassword(), b.getUser().getTelephone(), null);
            BookDTO book = new BookDTO(b.getBook().getId(), b.getBook().getTitle(), b.getBook().getAuthor(), b.getBook().getGenre(), b.getBook().getStatus(), null);
            list.add(new BorrowingBookDTO(b.getId(),user,book,b.getBorrowDate(),b.getReturnDate(),b.getStatus()));
        }
        return list;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return borrowingDaoImpl.generateNextId();
    }

    @Override
    public List<BorrowingBookDTO> getPendingList() throws SQLException, ClassNotFoundException {
        List<BorrowingBooks> pendingList = borrowingDaoImpl.getPendingList();
        List<BorrowingBookDTO> list = new ArrayList<>();

        for(BorrowingBooks b : pendingList){
            UserDTO user = new UserDTO(b.getUser().getName(), b.getUser().getEmail(), b.getUser().getPassword(), b.getUser().getTelephone(), null);
            BookDTO book = new BookDTO(b.getBook().getId(), b.getBook().getTitle(), b.getBook().getAuthor(), b.getBook().getGenre(), b.getBook().getStatus(), null);
            list.add(new BorrowingBookDTO(b.getId(),user,book,b.getBorrowDate(),b.getReturnDate(),b.getStatus()));
        }
        return list;
    }

    @Override
    public List<BorrowingBookDTO> getUserList(String email) throws SQLException, ClassNotFoundException {
        List<BorrowingBooks> pendingList = borrowingDaoImpl.getUserList(email);
        List<BorrowingBookDTO> list = new ArrayList<>();

        for(BorrowingBooks b : pendingList){
            UserDTO user = new UserDTO(b.getUser().getName(), b.getUser().getEmail(), b.getUser().getPassword(), b.getUser().getTelephone(), null);
            BookDTO book = new BookDTO(b.getBook().getId(), b.getBook().getTitle(), b.getBook().getAuthor(), b.getBook().getGenre(), b.getBook().getStatus(), null);
            list.add(new BorrowingBookDTO(b.getId(),user,book,b.getBorrowDate(),b.getReturnDate(),b.getStatus()));
        }
        return list;
    }

    @Override
    public List<BorrowingBookDTO> getNotReturnList(LocalDate date) throws SQLException, ClassNotFoundException {
        List<BorrowingBooks> pendingList = borrowingDaoImpl.getNotReturnList(date);
        List<BorrowingBookDTO> list = new ArrayList<>();

        for (BorrowingBooks b : pendingList) {
            UserDTO user = new UserDTO(b.getUser().getName(), b.getUser().getEmail(), b.getUser().getPassword(), b.getUser().getTelephone(), null);
            BookDTO book = new BookDTO(b.getBook().getId(), b.getBook().getTitle(), b.getBook().getAuthor(), b.getBook().getGenre(), b.getBook().getStatus(), null);
            list.add(new BorrowingBookDTO(b.getId(), user, book, b.getBorrowDate(), b.getReturnDate(), b.getStatus()));
        }
        return list;
    }
}

