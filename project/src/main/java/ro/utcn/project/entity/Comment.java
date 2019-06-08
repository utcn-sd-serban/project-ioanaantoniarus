package ro.utcn.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int id;
    private String text;
    private String username;
    private String commentDate;
    private int reviewId;

    public Comment(String text,String username,String commentDate,int reviewId){
        this.text=text;
        this.username=username;
        this.commentDate=commentDate;
        this.reviewId=reviewId;
    }
}
