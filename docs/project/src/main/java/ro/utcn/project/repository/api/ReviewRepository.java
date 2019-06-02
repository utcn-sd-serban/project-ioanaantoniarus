package ro.utcn.project.repository.api;

import ro.utcn.project.entity.Review;

import java.util.List;

public interface ReviewRepository {

    Review save(Review review);

    void delete(int id);

    void update(Review review);

    List<Review> findBookReview(int bookId);
}
