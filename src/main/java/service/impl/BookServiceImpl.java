package service.impl;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import repository.BookRepository;
import service.BookService;

public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Iterable<Book> findAllByNameContaining(String name) {
        return bookRepository.findAllByNameContaining(name);
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book delete(Integer id) {
        Book book = findById(id);
        bookRepository.delete(id);
        return book;
    }
}
