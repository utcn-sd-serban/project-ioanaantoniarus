package ro.utcn.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.project.dto.ReviewDTO;
import ro.utcn.project.entity.Review;
import ro.utcn.project.repository.api.RepositoryFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<ReviewDTO> findAllBookReviews(int bookId){
        List<ReviewDTO> reviews=repositoryFactory.createReviewRepository().findBookReview(bookId).stream()
                .map(ReviewDTO::ofEntity)
                .collect(Collectors.toList());
        return reviews;
    }

    @Transactional
    public ReviewDTO addReview(String title, String text, String username, int bookId){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime= LocalDateTime.now();
        String date=localDateTime.format(formatter);
        return ReviewDTO.ofEntity(repositoryFactory.createReviewRepository().save(new Review(bookId,title,username,text,date)));
    }


}
