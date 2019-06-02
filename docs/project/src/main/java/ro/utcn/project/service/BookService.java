package ro.utcn.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.project.dto.BookDTO;
import ro.utcn.project.entity.Book;
import ro.utcn.project.entity.Genre;
import ro.utcn.project.entity.Rating;
import ro.utcn.project.exception.BookNotFoundException;
import ro.utcn.project.exception.GenreNotFoundException;
import ro.utcn.project.exception.RatingNotFoundException;
import ro.utcn.project.repository.api.BookRepository;
import ro.utcn.project.repository.api.RepositoryFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final RepositoryFactory repositoryFactory;
    private final GenreService genreService;

    @Transactional
   public List<BookDTO> listBooks(){
       List<BookDTO> books=repositoryFactory.createBookRepository().findAll().stream()
               .map(BookDTO::ofEntity)
               .collect(Collectors.toList());
       for(BookDTO book:books){
           book.setRating(repositoryFactory.createRatingRepository().getBookRating(book.getId()));
       }
       return books;
   }

    @Transactional
    public BookDTO addBook(String title, String author, String isbn, String genres){
        Book book=repositoryFactory.createBookRepository().save(new Book(title, author, isbn));
        repositoryFactory.createRatingRepository().createRating(book.getId());
        String[] tokens=genres.split(" ");
        for(String token:tokens){
            addGenreToBook(book.getId(), genreService.addGenre(token));
        }
        BookDTO output=BookDTO.ofEntity(book);
        //eventPublisher.publishEvent(new QuestionCreatedEvent(output));
        return output;
    }

   @Transactional
   public BookDTO findByIsbn(String isbn){
       BookRepository repository=repositoryFactory.createBookRepository();
       Book book=repository.findByIsbn(isbn);
       return BookDTO.ofEntity(book);
   }

    @Transactional
    public Book addGenreToBook(int id, Genre genre){
        BookRepository repository = repositoryFactory.createBookRepository();
        Book book=repository.findById(id).orElseThrow(BookNotFoundException::new);
        List<Genre> genres=book.getGenres();
        genres.add(genre);
        book.setGenres(genres);
        repository.save(book);
        return book;
    }

   @Transactional
   public List<BookDTO> findByTitle(String title){
        List<BookDTO> books=repositoryFactory.createBookRepository().findByTitle(title).stream()
                .map(BookDTO::ofEntity)
                .collect(Collectors.toList());
       for(BookDTO book:books){
           book.setRating(repositoryFactory.createRatingRepository().getBookRating(book.getId()));
       }
        return books;
   }

   @Transactional
   public List<BookDTO> findByGenre(String name){
        Genre genre=repositoryFactory.createGenreRepository().findByName(name);
        List<BookDTO> books=repositoryFactory.createBookRepository().findByGenre(genre).stream()
                .map(BookDTO::ofEntity)
                .collect(Collectors.toList());
       for(BookDTO book:books){
           book.setRating(repositoryFactory.createRatingRepository().getBookRating(book.getId()));
       }
        return books;
   }

    @Transactional
   public void rateBook(int rating,int bookId){
        Rating ratingInfo=repositoryFactory.createRatingRepository().getRatingInfo(bookId).orElseThrow(RatingNotFoundException::new);
        ratingInfo.setRatingSum(ratingInfo.getRatingSum()+rating);
        ratingInfo.setTotalNumberOfRatings(ratingInfo.getTotalNumberOfRatings()+1);
        ratingInfo.setFinalRating(ratingInfo.getRatingSum()/ratingInfo.getTotalNumberOfRatings());
        repositoryFactory.createRatingRepository().updateRating(bookId,ratingInfo);
   }
}
