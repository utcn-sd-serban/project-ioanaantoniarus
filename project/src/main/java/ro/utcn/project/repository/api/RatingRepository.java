package ro.utcn.project.repository.api;

import ro.utcn.project.entity.Rating;

import java.util.Optional;

public interface RatingRepository {

    Optional<Rating> getRatingInfo(int bookId);

    void updateRating(int id, Rating rating);

    void createRating(int id);

    float getBookRating(int bookId);


}
