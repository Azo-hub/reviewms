package com.reviewms.service.impl;


import com.reviewms.domain.Review;
import com.reviewms.repository.ReviewRepository;
import com.reviewms.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;


    
    @Override
    public List<Review> getAllReview(long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review addReview(long companyId, Review review) {
        review.setCompanyId(companyId);
        return reviewRepository.save(review);
    }

    @Override
    public Review getReview(long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public Boolean updateReview(long reviewId, Review review) {
        if(reviewRepository.findById(reviewId).isPresent()){
            reviewRepository.findById(reviewId).ifPresent(dbReview -> {
                dbReview.setTitle(review.getTitle());
                dbReview.setDescription(review.getDescription());
                dbReview.setRating(review.getRating());

                reviewRepository.save(dbReview);
            });
          return true;

        }

        return false;
    }

    @Override
    public Boolean deleteReview(long reviewId) {
        if(reviewRepository.findById(reviewId).isPresent()){
           reviewRepository.deleteById(reviewId);
           return true;
        }
        return false;
    }
}
