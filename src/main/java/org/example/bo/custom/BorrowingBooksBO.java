package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.BookDTO;
import org.example.dto.BorrowingBookDTO;

public interface BorrowingBooksBO extends SuperBO {
    void borrowBook(BorrowingBookDTO borrowDTO, BookDTO book);
}
