package ro.utcn.project.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ro.utcn.project.entity.Comment;
import ro.utcn.project.repository.api.CommentRepository;

import java.util.List;

@RequiredArgsConstructor
public class JdbcCommentRepository implements CommentRepository {
    private final JdbcTemplate template;

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(int id) {

    }

    @Override
    public List<Comment> findBookComment(int bookId) {
        return null;
    }
}
