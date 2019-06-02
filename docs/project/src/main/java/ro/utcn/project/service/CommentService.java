package ro.utcn.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.project.entity.Comment;
import ro.utcn.project.repository.api.RepositoryFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public Comment addComment(String text, String username, int bookId){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime= LocalDateTime.now();
        String date=localDateTime.format(formatter);
        Comment comment=repositoryFactory.createCommentRepository().save(new Comment(text,username,date,bookId));
        return comment;
    }
}
