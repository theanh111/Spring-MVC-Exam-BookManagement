package service;

import model.Book;

public interface BookService {
    Iterable<Book> findAll();

    Iterable<Book> findAllByNameContaining(String name);

    Book findById(Integer id);

    Book save(Book book);

    Book delete(Integer id);
}
