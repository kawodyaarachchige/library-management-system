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
public class User {

    String name;
    @Id
    String email;
    String password;
    @Column(unique = true)
    int telephone;

    @ManyToOne
    Branch branch;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<BorrowingBooks> borrowBooks;
}
