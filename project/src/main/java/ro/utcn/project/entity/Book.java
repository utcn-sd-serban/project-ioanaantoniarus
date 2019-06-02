package ro.utcn.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;
    private String name;
    private String author;
    private String isbn;
    private float rating;
    private List<Genre> genres;
    private List<Review> reviews;

    public Book(String name, String author, String isbn){
        this.name=name;
        this.author=author;
        this.isbn=isbn;
    }

    public Book(String name, String author, String isbn, float rating){
        this.name=name;
        this.author=author;
        this.isbn=isbn;
        this.rating=rating;
    }

    public Book(String name, String author, String isbn, float rating,List<Genre> genres){
        this.name=name;
        this.author=author;
        this.isbn=isbn;
        this.rating=rating;
        this.genres=genres;
    }

    public Book(int id, String name, String author, String isbn){
        this.id=id;
        this.name=name;
        this.author=author;
        this.isbn=isbn;
    }

    public String genresToString(){
        String bookGenres="";
        for(Genre genre: this.getGenres()){
            bookGenres+=" "+genre.getName();
        }
        return bookGenres;
    }
}
