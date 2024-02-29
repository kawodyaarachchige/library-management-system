package org.example.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminDTO {

    private String id;
    private String name;
    private String email;
    private String password;
}
