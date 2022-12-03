package com.wemove.wemove_backend.services;

import com.wemove.wemove_backend.entities.Review;
import com.wemove.wemove_backend.repositories.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewsRepository reviewsRepository;

    public ReviewService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }


    public List<Review> getAllReviews(String moverEmail){
        return this.reviewsRepository.findAllReviewByMoverEmail(moverEmail);
    }

    public Review addReview(Review review){
       return this.reviewsRepository.save(review);
    }

}
