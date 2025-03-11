package com.example.bookmanagement.service;

import com.example.bookmanagement.dto.BookDTO;
import com.example.bookmanagement.entity.Book;
import com.example.bookmanagement.exception.BadRequestException;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import com.example.bookmanagement.mapper.BookMapper;
import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Retrieve all books from the database and convert them to DTOs
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Retrieve a book by its ID, using custom ResourceNotFoundException
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        return BookMapper.toDTO(book);
    }

    // Search for books by title, author, or both
    public List<BookDTO> searchBooks(String title, String author) {
        List<Book> books;

        if (title != null && author != null) {
            books = bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author);
        } else if (title != null) {
            books = bookRepository.findByTitleContainingIgnoreCase(title);
        } else if (author != null) {
            books = bookRepository.findByAuthorContainingIgnoreCase(author);
        } else {
            books = bookRepository.findAll();
        }

        return books.stream().map(BookMapper::toDTO).collect(Collectors.toList());
    }

    // Create a new book entry in the database
    @Transactional
    public BookDTO createBook(BookDTO bookDTO) {
        // Add validation if needed
        if (bookDTO.getTitle() == null || bookDTO.getTitle().trim().isEmpty()) {
            throw new BadRequestException("Book title cannot be empty");
        }
        if (bookDTO.getAuthor() == null || bookDTO.getAuthor().trim().isEmpty()) {
            throw new BadRequestException("Book author cannot be empty");
        }

        Book book = BookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return BookMapper.toDTO(savedBook);
    }

    // Update an existing book in the database
    @Transactional
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        // Add validation if needed
        if (bookDTO.getTitle() == null || bookDTO.getTitle().trim().isEmpty()) {
            throw new BadRequestException("Book title cannot be empty");
        }
        if (bookDTO.getAuthor() == null || bookDTO.getAuthor().trim().isEmpty()) {
            throw new BadRequestException("Book author cannot be empty");
        }

        // Update book properties
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setDescription(bookDTO.getDescription());

        Book updatedBook = bookRepository.save(existingBook);
        return BookMapper.toDTO(updatedBook);
    }

    // Partially update a book by modifying only the provided fields
    @Transactional
    public BookDTO patchBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        boolean updated = false;

        if (bookDTO.getTitle() != null) {
            if (bookDTO.getTitle().trim().isEmpty()) {
                throw new BadRequestException("Book title cannot be empty");
            }
            existingBook.setTitle(bookDTO.getTitle());
            updated = true;
        }
        if (bookDTO.getAuthor() != null) {
            if (bookDTO.getAuthor().trim().isEmpty()) {
                throw new BadRequestException("Book author cannot be empty");
            }
            existingBook.setAuthor(bookDTO.getAuthor());
            updated = true;
        }
        if (bookDTO.getDescription() != null) {
            existingBook.setDescription(bookDTO.getDescription());
            updated = true;
        }

        if (!updated) {
            throw new BadRequestException("At least one field must be updated");
        }

        Book updatedBook = bookRepository.save(existingBook);
        return BookMapper.toDTO(updatedBook);
    }

    @Transactional
    public ResponseDTO deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book", "id", id);
        }
        bookRepository.deleteById(id);
        return new ResponseDTO("Book with ID " + id + " has been successfully deleted.");
    }
}
