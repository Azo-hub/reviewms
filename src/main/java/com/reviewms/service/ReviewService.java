package com.reviewms.service;


import com.reviewms.domain.Review;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReview(long id);
    Review addReview(long companyId, @RequestBody Review review);
    Review getReview(long reviewId);
    Boolean updateReview(long reviewId, @RequestBody Review review);
    Boolean deleteReview(long reviewId);
}
