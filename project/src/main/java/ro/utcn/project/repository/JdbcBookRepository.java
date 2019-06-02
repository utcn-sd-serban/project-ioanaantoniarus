package ro.utcn.project.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.project.entity.Book;
import ro.utcn.project.entity.Genre;
import ro.utcn.project.entity.Rating;
import ro.utcn.project.entity.Review;
import ro.utcn.project.repository.api.BookRepository;

import java.util.*;

@RequiredArgsConstructor
public class JdbcBookRepository implements BookRepository {
    private final JdbcTemplate template;

    @Override
    public List<Book> findAll() {
        List<Book> books=template.query("SELECT * FROM book",new BookMapper());
        return books;
    }

    @Override
    public Book save(Book book) {
        if(book.getId()!= 0){
            update(book);
            updateGenres(book);
        }
        else{
            book.setId(insert(book));
        }
        return book;
    }

    @Override
    public Optional<Book> findById(int id) {
        List<Book> books=template.query("SELECT * FROM book WHERE id= ?",
                new Object[]{ id },
                new BookMapper());
        if(books.isEmpty()){
            return Optional.empty();
        }
        else{
            books.get(0).setGenres(findGenres(books.get(0).getId()));
            books.get(0).setReviews(findBookReviews(books.get(0).getId()));
            return Optional.of(books.get(0));
        }
    }

    @Override
    public void update(Book book) {
        template.update("UPDATE book SET name =?, author=?, isbn=? WHERE id= ?",
                book.getName(),book.getAuthor(),book.getIsbn(),book.getId());
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Book findByIsbn(String isbn){
        List<Book> books=template.query("SELECT * FROM book WHERE isbn= ?",
                new Object[]{ isbn },
                new BookMapper());
        if(books.isEmpty()){
            return null;
        }
        else{
            books.get(0).setGenres(findGenres(books.get(0).getId()));
            books.get(0).setReviews(findBookReviews(books.get(0).getId()));
            return books.get(0);
        }
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> books=template.query("SELECT * FROM book WHERE name LIKE CONCAT('%' , ? , '%')", new Object[]{title}, new BookMapper());
        for(Book book: books){
            book.setRating(getBookRating(book.getId()));
            book.setGenres(findGenres(book.getId()));
            book.setReviews(findBookReviews(book.getId()));
        }
        return books;
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        List<Book> books=template.query("SELECT * FROM book INNER JOIN book_genre ON book.id=book_genre.id_book WHERE book_genre.id_genre=?",new Object[]{genre.getId()},new BookMapper());

        for(Book book:books){
            book.setGenres(findGenres(book.getId()));
            book.setReviews(findBookReviews(book.getId()));
            book.setRating(getBookRating(book.getId()));
        }

        return books;
    }

    private List<Genre> findGenres(int id){
        return template.query("SELECT * FROM genre INNER JOIN book_genre ON genre.id=book_genre.id_genre WHERE book_genre.id_book=?",new Object[]{id},new GenreMapper());
    }

    private List<Review> findBookReviews(int id){
        List reviews=template.query("SELECT * FROM review WHERE id_book= ?",new Object[]{ id },
                new ReviewMapper());
        return reviews;
    }


    private float getBookRating(int bookId){
        List<Rating> rating= template.query("SELECT * FROM rating WHERE id=?",new Object[]{bookId},new RatingMapper());
        return rating.get(0).getFinalRating();
    }

    private int insert(Book book){
        SimpleJdbcInsert insert= new SimpleJdbcInsert(template);
        insert.setTableName("book");
        insert.setGeneratedKeyName("id");
        Map<String, Object> data= new HashMap<>();
        data.put("name", book.getName());
        data.put("author",book.getAuthor());
        data.put("isbn",book.getIsbn());
        return insert.executeAndReturnKey(data).intValue();
    }

    private void updateGenres(Book book){
        List<Genre> genres=book.getGenres();
        for(Genre genre:genres){
            List<Integer> list=template.query("SELECT id_genre FROM book_genre WHERE id_genre=? and id_book=?",new Object[]{genre.getId(),book.getId()},
                    (resultSet,i)->resultSet.getInt("id_genre"));
            if(list.isEmpty()){
                template.update("INSERT INTO book_genre (id_genre, id_book) VALUES (?,?)",genre.getId(),book.getId());
            }
        }
    }
}
