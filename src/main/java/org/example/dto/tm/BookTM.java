package org.example.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookTM {
    String id;
    String title;
    String author;
    String status;
}
