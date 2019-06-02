package ro.utcn.project.repository.api;

import ro.utcn.project.entity.Comment;

import java.util.List;

public interface CommentRepository {

    Comment save(Comment comment);

    void delete(int id);

    void update(int id);

    List<Comment> findBookComment(int bookId);

}
