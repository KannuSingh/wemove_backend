package com.wemove.wemove_backend.repositories;

import com.wemove.wemove_backend.entities.PriceQuote;
import com.wemove.wemove_backend.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT r FROM Review r WHERE r.moverEmail = :email")
    List<Review> findAllReviewByMoverEmail(String email);

}
