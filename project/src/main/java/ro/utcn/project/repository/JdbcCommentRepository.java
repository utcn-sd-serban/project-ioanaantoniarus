package ro.utcn.project.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.project.entity.Comment;
import ro.utcn.project.repository.api.CommentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class JdbcCommentRepository implements CommentRepository {
    private final JdbcTemplate template;

    @Override
    public Comment save(Comment comment) {
        if(comment.getId()!= 0){
            update(comment);
        }
        else{
            comment.setId(insert(comment));
        }
        return comment;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Comment comment) {
        template.update("UPDATE comment SET text=? WHERE id=?",
                comment.getText(),comment.getId());
    }

    @Override
    public List<Comment> findReviewComment(int reviewId) {
        List<Comment> comments=template.query("SELECT * FROM comment WHERE review_id= ?",new Object[]{ reviewId },
                new CommentMapper());
        return comments;
    }

    private int insert(Comment comment){
        SimpleJdbcInsert insert= new SimpleJdbcInsert(template);
        insert.setTableName("comment");
        insert.setGeneratedKeyName("id");
        Map<String, Object> data= new HashMap<>();
        data.put("text", comment.getText());
        data.put("username",comment.getUsername());
        data.put("date",comment.getCommentDate());
        data.put("review_id",comment.getReviewId());
        return insert.executeAndReturnKey(data).intValue();
    }
}
