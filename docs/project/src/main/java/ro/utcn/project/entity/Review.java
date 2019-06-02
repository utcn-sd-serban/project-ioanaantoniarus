package ro.utcn.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private int id;
    private int bookId;
    private String title;
    private String username;
    private String text;
    private String reviewDate;

    public Review(int bookId, String title, String username, String text, String reviewDate){
        this.bookId=bookId;
        this.title=title;
        this.username=username;
        this.text=text;
        this.reviewDate=reviewDate;
    }
}
