package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Book {
    @Id
    String id;
    String title;
    String author;
    String genre;
    String status;

    @ManyToOne
    Branch branch;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    List<BorrowingBooks> borrowBooks;
}