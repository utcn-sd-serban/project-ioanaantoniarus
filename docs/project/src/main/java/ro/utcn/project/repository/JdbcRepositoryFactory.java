package ro.utcn.project.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.project.repository.api.*;

@Component
@RequiredArgsConstructor
public class JdbcRepositoryFactory implements RepositoryFactory {
    private final JdbcTemplate template;
    @Override
    public BookRepository createBookRepository() {
        return new JdbcBookRepository(template);
    }

    @Override
    public CommentRepository createCommentRepository() {
        return new JdbcCommentRepository(template);
    }

    @Override
    public ReviewRepository createReviewRepository() {
        return new JdbcReviewRepository(template);
    }

    @Override
    public GenreRepository createGenreRepository() {
        return new JdbcGenreRepository(template);
    }

    @Override
    public UserRepository createUserRepository() {
        return new JdbcUserRepository(template);
    }

    @Override
    public RatingRepository createRatingRepository() {
        return new JdbcRatingRepository(template);
    }
}
