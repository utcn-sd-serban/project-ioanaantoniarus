package ro.utcn.project.dto;

import lombok.Data;
import ro.utcn.project.entity.Comment;

@Data
public class CommentDTO {
    private int id;
    private String text;
    private String username;
    private String commentDate;

    public static CommentDTO ofEntity(Comment comment){
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setUsername(comment.getUsername());
        commentDTO.setText(comment.getText());
        commentDTO.setCommentDate(comment.getCommentDate());
        return commentDTO;
    }
}
