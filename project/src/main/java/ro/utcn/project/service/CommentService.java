package ro.utcn.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.project.dto.CommentDTO;
import ro.utcn.project.entity.Comment;
import ro.utcn.project.entity.User;
import ro.utcn.project.repository.api.RepositoryFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final RepositoryFactory repositoryFactory;
    private final UserService userService;

    @Transactional
    public CommentDTO addComment(String text, int reviewId){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime= LocalDateTime.now();
        String date=localDateTime.format(formatter);
        User user=userService.loadCurrentUser();
        Comment comment=repositoryFactory.createCommentRepository().save(new Comment(text,user.getUsername(),date,reviewId));
        return CommentDTO.ofEntity(comment);
    }

    @Transactional
    public List<CommentDTO> findAllComments(int reviewId){
        List<CommentDTO> comments=repositoryFactory.createCommentRepository().findReviewComment(reviewId).stream()
                .map(CommentDTO::ofEntity)
                .collect(Collectors.toList());
        return comments;
    }
}
