package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Borrowing_Books")
public class BorrowingBooks {
    @Id
    String id;
    @ManyToOne
    User user;
    @ManyToOne
    Book book;
    LocalDate borrowDate;
    LocalDate returnDate;
    String status;
}
