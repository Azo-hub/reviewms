package com.reviewms.controller;

import com.reviewms.domain.Review;
import com.reviewms.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReview(@RequestParam("companyId") long id) {

        return new ResponseEntity<>(reviewService.getAllReview(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestParam("companyId") long id, @RequestBody Review review) {
        Review createdReview = reviewService.addReview(id, review);
        if(createdReview != null){
            return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable("reviewId") long reviewId) {

        return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable("reviewId") long reviewId,
                                               @RequestBody Review review) {
        Boolean updated = reviewService.updateReview(reviewId, review);
        if(updated){
            return new ResponseEntity<>("updated successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") long reviewId) {
        boolean deleted = reviewService.deleteReview(reviewId);
        if(deleted){
            return new ResponseEntity<>("Review successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not successfully deleted", HttpStatus.BAD_REQUEST);
    }

}
