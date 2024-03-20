package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Admin {
    @Id
    @Column(unique = true, nullable = false)
    String email;
    String name;
    @Column(unique = true)
    int telephone;
    String password;
}