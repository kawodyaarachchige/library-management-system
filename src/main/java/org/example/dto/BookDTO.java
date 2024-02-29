package org.example.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDTO {

    private String id;
    private String title;
    private String author;
    private String genre;
    private String status;
}
