package ro.utcn.project.repository.api;

import ro.utcn.project.entity.Book;
import ro.utcn.project.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Book save(Book book);

    void update(Book book);

    void delete(int id);

    Optional<Book> findById(int id);

    Book findByIsbn(String isbn);

    List<Book> findByTitle(String title);

    List<Book> findByGenre(Genre genre);

}
