package ro.utcn.project.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.project.entity.Review;
import ro.utcn.project.repository.api.ReviewRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class JdbcReviewRepository implements ReviewRepository {
    private final JdbcTemplate template;

    @Override
    public Review save(Review review) {
        if(review.getId()!= 0){
            update(review);
        }
        else{
            review.setId(insert(review));
        }
        return review;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Review review) {
        template.update("UPDATE review SET title=?,text=? WHERE id=?",
                review.getTitle(),review.getText(),review.getId());
    }

    @Override
    public List<Review> findBookReview(int bookId) {
        List<Review> reviews=template.query("SELECT * FROM review WHERE id_book= ?",new Object[]{ bookId },
                new ReviewMapper());
        return reviews;
    }

    private int insert(Review review){
        SimpleJdbcInsert insert= new SimpleJdbcInsert(template);
        insert.setTableName("review");
        insert.setGeneratedKeyName("id");
        Map<String, Object> data= new HashMap<>();
        data.put("title", review.getTitle());
        data.put("username",review.getUsername());
        data.put("text",review.getText());
        data.put("date",review.getReviewDate());
        data.put("id_book",review.getBookId());
        return insert.executeAndReturnKey(data).intValue();
    }
}
