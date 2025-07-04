package com.abhijith.library.controller;

import com.abhijith.library.model.Book;
import com.abhijith.library.exception.ApiResponse;
import com.abhijith.library.service.BookService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(service.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Book>> createBook(@Valid @RequestBody Book book) {
        Book savedBook = service.addBook(book);
        return ResponseEntity.ok(new ApiResponse<>("Book created successfully!", savedBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = service.updateBook(id, book);
        return ResponseEntity.ok(new ApiResponse<>("Book updated successfully", updatedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return ResponseEntity.ok(new ApiResponse<>("Book deleted successfully!", "Book with ID " + id + " has been removed."));
    }

    @GetMapping("/author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String name) {
        List<Book> books = service.getBooksByAuthor(name);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/price")
    public ResponseEntity<ApiResponse<List<Book>>> getBooksByMaxPrice(@RequestParam double price) {
        List<Book> books = service.getBooksByMaxPrice(price);
        if (books.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>("Sorry, no books under the price range", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("Books with price <= " + price, books));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable Long id) {
        Book book = service.getBookById(id);
        return ResponseEntity.ok(new ApiResponse<>("Book found", book));
    }
}
