package com.example.bookmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Data // Generates getters and setters
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
