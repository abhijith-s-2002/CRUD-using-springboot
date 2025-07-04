package com.abhijith.library.service;

import com.abhijith.library.model.Book;
import com.abhijith.library.repository.BookRepository;
import com.abhijith.library.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book newBookData) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (newBookData.getTitle() != null) book.setTitle(newBookData.getTitle());
        if (newBookData.getAuthor() != null) book.setAuthor(newBookData.getAuthor());
        if (newBookData.getPublishedyr() != null) book.setPublishedyr(newBookData.getPublishedyr());
        if (newBookData.getPrice() != null) book.setPrice(newBookData.getPrice());
        if (newBookData.getSummary() != null) book.setSummary(newBookData.getSummary());

        return bookRepository.save(book);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author);
    }

    public List<Book> getBooksByMaxPrice(double price) {
        return bookRepository.findByPriceLessThanEqual(price);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
}
