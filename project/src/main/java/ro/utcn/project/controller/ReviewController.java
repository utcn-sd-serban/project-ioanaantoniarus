package ro.utcn.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.project.dto.ReviewDTO;
import ro.utcn.project.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/books/{id}/reviews")
    public ReviewDTO createReview(@RequestBody ReviewDTO dto, @PathVariable int id){
        return reviewService.addReview(dto.getTitle(),dto.getText(),id);
    }

    @GetMapping("/books/{id}/reviews")
    public List<ReviewDTO> loadBookReviews(@PathVariable int id){
        return reviewService.findAllBookReviews(id);
    }
}
