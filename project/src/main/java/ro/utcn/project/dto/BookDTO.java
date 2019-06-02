package ro.utcn.project.dto;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import ro.utcn.project.entity.Book;

@Data
public class BookDTO {
    private int id;
    private String name;
    private String author;
    private String isbn;
    private float rating;
    private String genres;

    public static BookDTO ofEntity(Book book){
        BookDTO bookDTO=new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setRating(book.getRating());
        if(!CollectionUtils.isEmpty(book.getGenres()))
            bookDTO.setGenres(book.genresToString());
        return bookDTO;
    }
}
