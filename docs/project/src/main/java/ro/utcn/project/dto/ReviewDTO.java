package ro.utcn.project.dto;

import lombok.Data;
import ro.utcn.project.entity.Review;

@Data
public class ReviewDTO {
    private String title;
    private String username;
    private String text;
    private String reviewDate;

    public static ReviewDTO ofEntity(Review review){
        ReviewDTO reviewDTO=new ReviewDTO();
        reviewDTO.setUsername(review.getUsername());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setText(review.getText());
        reviewDTO.setReviewDate(review.getReviewDate());
        return reviewDTO;
    }
}
