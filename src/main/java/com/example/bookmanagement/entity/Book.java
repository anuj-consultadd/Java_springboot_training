// Entity are MODELS.
// Where we define our tables (JPA Entity)

package com.example.bookmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity // Marks this as a JPA Entity (table)
@Table(name = "books") // Table name in DB
@Data
@NoArgsConstructor // automatically generates the default no arg constructor
@AllArgsConstructor  // automatically generates the arg constructor.
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments ID
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(length = 1000)
    private String description;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist // Automatically sets timestamps createdAt, when the book is created
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate // Automatically sets timestamps updatedAt when the book is updated.
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}