package org.example.bo.custom.impl;

import org.example.bo.custom.BookBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.BookDAO;
import org.example.dto.BookDTO;
import org.example.dto.BranchDTO;
import org.example.entity.Book;
import org.example.entity.Branch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBOImpl  implements BookBO {

    BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);
    @Override
    public boolean save(BookDTO dto) throws SQLException, ClassNotFoundException {
        BranchDTO branch = dto.getBranch();
        Branch branch1 = new Branch(branch.getId(), branch.getLocation(), branch.getTelephone(), branch.getEmail(), branch.getAddress(),null,null);
        return bookDAO.save(new Book(dto.getId(),dto.getTitle(),dto.getAuthor(),dto.getGenre(),dto.getStatus(), branch1,null));
    }

    @Override
    public boolean update(BookDTO dto) throws SQLException, ClassNotFoundException {
        BranchDTO branch = dto.getBranch();
        Branch branch1 = new Branch(branch.getId(), branch.getLocation(), branch.getTelephone(), branch.getEmail(), branch.getAddress(),null,null);
        return bookDAO.update(new Book(dto.getId(),dto.getTitle(),dto.getAuthor(),dto.getGenre(),dto.getStatus(), branch1,null));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return bookDAO.delete(id);
    }

    @Override
    public BookDTO search(String id) throws SQLException, ClassNotFoundException {
        Book search = bookDAO.search(id);

        if (search == null) {
            return null;
        } else {
            return new BookDTO(search.getId(),search.getTitle(),search.getAuthor(),search.getGenre(),search.getStatus(),new BranchDTO(search.getBranch().getId(), search.getBranch().getLocation(), search.getBranch().getTelephone(), search.getBranch().getEmail(), search.getBranch().getAddress()));
        }
    }

    @Override
    public List<BookDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Book> all = bookDAO.getAll();
        List<BookDTO> list = new ArrayList<>();

        if (all == null) {
            return null;
        } else {
            for (Book book : all) {
                list.add(new BookDTO(book.getId(),book.getTitle(),book.getAuthor(),book.getGenre(),book.getStatus(),
                        new BranchDTO(book.getBranch().getId(), book.getBranch().getLocation(), book.getBranch().getTelephone(),
                                book.getBranch().getEmail(), book.getBranch().getAddress())));
            }
            return list;
        }
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return bookDAO.generateNextId();
    }
    @Override
    public List<BookDTO> searchByTitle(String title, String branch) throws SQLException, ClassNotFoundException{
        List<Book> list = bookDAO.searchByTitle(title, branch);
        List<BookDTO> list1 = new ArrayList<>();

        for (Book book:list) {
            BookDTO bookDTO = new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getStatus(), new BranchDTO(book.getBranch().getId(), book.getBranch().getLocation(), book.getBranch().getTelephone(), book.getBranch().getEmail(), book.getBranch().getAddress()));
            list1.add(bookDTO);
        }

        return list1;

    }

    @Override
    public List<BookDTO> searchByBranch(String branch) throws SQLException, ClassNotFoundException {
        List<Book> list = bookDAO.searchByBranch(branch);
        List<BookDTO> list1 = new ArrayList<>();

        for (Book book:list) {
            BookDTO bookDTO = new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getStatus(), new BranchDTO(book.getBranch().getId(), book.getBranch().getLocation(), book.getBranch().getTelephone(), book.getBranch().getEmail(), book.getBranch().getAddress()));
            list1.add(bookDTO);
        }

        return list1;
    }

    @Override
    public List<Object[]> getCounts() throws SQLException, ClassNotFoundException {
        return bookDAO.getCounts();
    }

    @Override
    public List<Object[]> getBookCountsByTitle() throws SQLException, ClassNotFoundException {
        return bookDAO.getBookCountsByTitle();
    }

}