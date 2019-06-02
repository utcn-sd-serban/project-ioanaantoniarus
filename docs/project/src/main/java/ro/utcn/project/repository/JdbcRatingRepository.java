package ro.utcn.project.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.project.entity.Rating;
import ro.utcn.project.repository.api.RatingRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcRatingRepository implements RatingRepository {
    private final JdbcTemplate template;


    @Override
    public Optional<Rating> getRatingInfo(int bookId){
        List<Rating> rating=template.query("SELECT * FROM rating WHERE id=?",new Object[]{bookId},new RatingMapper());
        if(rating.isEmpty()){
            return Optional.empty();
        }
        else{
            return Optional.of(rating.get(0));
        }
    }

    @Override
    public void updateRating(int id, Rating rating){
        template.update("UPDATE rating SET rating_sum =?, total_ratings=?, final_rating=? WHERE id= ?",
                rating.getRatingSum(),rating.getTotalNumberOfRatings(),rating.getFinalRating(),id);
    }

    @Override
    public void createRating(int id){
        template.update(
                "INSERT INTO rating (id, rating_sum,total_ratings,final_rating) VALUES (?, ?, ?, ?)",
                id, 0, 0, 0);
    }

    @Override
    public float getBookRating(int bookId){
        List<Rating> rating= template.query("SELECT * FROM rating WHERE id=?",new Object[]{bookId},new RatingMapper());
        return rating.get(0).getFinalRating();
    }
}
