package com.example.bookmanagement.mapper;

import com.example.bookmanagement.dto.BookDTO;
import com.example.bookmanagement.entity.Book;

public class BookMapper {

    public static BookDTO toDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getCreatedAt(),
                book.getUpdatedAt()
        );
    }

    public static Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        return book;
    }
}
