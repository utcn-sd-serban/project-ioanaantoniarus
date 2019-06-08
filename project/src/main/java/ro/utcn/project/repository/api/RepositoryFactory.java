package ro.utcn.project.repository.api;

public interface RepositoryFactory {

    BookRepository createBookRepository();

    CommentRepository createCommentRepository();

    ReviewRepository createReviewRepository();

    GenreRepository createGenreRepository();

    UserRepository createUserRepository();

    RatingRepository createRatingRepository();

}
