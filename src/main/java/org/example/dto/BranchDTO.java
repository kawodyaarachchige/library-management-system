package org.example.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BranchDTO {
    String id;
    String location;
    int telephone;
    String email;
    String address;
}
