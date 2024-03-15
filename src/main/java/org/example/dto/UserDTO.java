package org.example.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    String name;
    String email;
    String password;
    int telephone;
    BranchDTO Branch;
}
