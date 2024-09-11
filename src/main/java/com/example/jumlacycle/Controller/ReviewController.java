package com.example.jumlacycle.Controller;

import com.example.jumlacycle.Model.Review;
import com.example.jumlacycle.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/review")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/get-all")
    public ResponseEntity getAllReviews() {
        return ResponseEntity.status(200).body(reviewService.findAllReviews());
    }

    //all comment endpoint need user class.

//    @PostMapping("/add-review/{orderId}")
//    public ResponseEntity addReview(@PathVariable Integer orderId,@Valid @RequestBody Review review, @AuthenticationPrincipal User user) {
//        reviewService.addReviewForProduct(orderId,review,user.getId);
//        return ResponseEntity.status(200).body("review added successfully");
//    }

//    @PutMapping("/update/{reviewId}")
//    public ResponseEntity updateReview(@RequestBody Review review, @AuthenticationPrincipal User user, @PathVariable Integer reviewId) {
//        reviewService.updateReviewForProduct(reviewId,review,user.getId);
//        return ResponseEntity.status(200).body("update successful");
//    }

//    @DeleteMapping("/delete/{reviewId}")
//    public ResponseEntity deleteReview(@PathVariable Integer reviewId,@AuthenticationPrincipal User user) {
//        reviewService.deleteReviewForProduct(reviewId,user.getId);
//    }
}
