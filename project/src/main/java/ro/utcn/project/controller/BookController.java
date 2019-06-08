package ro.utcn.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.project.dto.BookDTO;
import ro.utcn.project.dto.RatingDTO;
import ro.utcn.project.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public List<BookDTO> readAll(){
        return bookService.listBooks();
    }

    @PostMapping("/books")
    public BookDTO createBook(@RequestBody BookDTO dto){
        return bookService.addBook(dto.getName(),dto.getAuthor(),dto.getIsbn(),dto.getGenres());
    }

    @GetMapping(value="/books",params="title")
    public List<BookDTO> readFilterTitle(@RequestParam("title") String title){
        return bookService.findByTitle(title);
    }

    @GetMapping(value="/books",params="genre")
    public List<BookDTO> readFilterGenre(@RequestParam("genre") String genre){
        return bookService.findByGenre(genre);
    }

    @GetMapping(value="/books",params="isbn")
    public BookDTO findByIsbn(@RequestParam("isbn") String isbn){
        return bookService.findByIsbn(isbn);
    }

    @PostMapping("/books/{id}")
    public void addRating(@RequestBody RatingDTO dto, @PathVariable int id){
        bookService.rateBook(Integer.parseInt(dto.getRating()),id);
    }
}
