package com.example.jumlacycle.Service;

import com.example.jumlacycle.API.ApiException;
import com.example.jumlacycle.Model.Customer;
import com.example.jumlacycle.Model.Order;
import com.example.jumlacycle.Model.Product;
import com.example.jumlacycle.Model.Review;
import com.example.jumlacycle.Repository.CustomerRepository;
import com.example.jumlacycle.Repository.OrderRepository;
import com.example.jumlacycle.Repository.ProductRepository;
import com.example.jumlacycle.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public Review findReviewById(Integer id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    public void addReviewForProduct(Integer orderId, Review review, Integer customerId) {
        Order order=orderRepository.findOrderById(orderId);
        if (order==null) {
            throw new RuntimeException("order not found to add review");
        }else {
            Customer customer=customerRepository.findCustomerById(customerId);
            if (customer==null) {
                throw new RuntimeException("Customer not found to add review");
            }else {
                customer.setReview(review);
                review.setOrder(order);
                reviewRepository.save(review);
                customerRepository.save(customer);
            }
        }
    }

    public void updateReviewForProduct(Integer reviewId, Review review, Integer customerId) {
        Customer customer=customerRepository.findCustomerById(customerId);
        if (customer==null) {
            throw new RuntimeException("Customer not found to update review");
        }else {
            Review oldReview=reviewRepository.findReviewById(reviewId);
            if (oldReview==null) {
                throw new RuntimeException("Review not found to update review");
            }else {
                Order order=orderRepository.findOrderById(review.getOrder().getId());
                if (order==null) {
                    throw new RuntimeException("Order not found to update review");
                }else {
                    oldReview.setOrder(order);
                    customer.setReview(oldReview);
                    oldReview.setRate(review.getRate());
                    oldReview.setDescription(review.getDescription());
                    reviewRepository.save(oldReview);
                    customerRepository.save(customer);
                }
            }
        }
    }

    public void deleteReviewForProduct(Integer reviewId, Integer customerId) {
        Customer customer=customerRepository.findCustomerById(customerId);
        Review review=reviewRepository.findReviewById(reviewId);
        if (customer==null) {
            throw new RuntimeException("Customer not found to delete review");
        }else {
            if (review==null) {
                throw new RuntimeException("Review not found to delete review");
            }else {
                if (customer.getReview().getId()==review.getId()) {
                    reviewRepository.deleteById(reviewId);
                }else {
                    throw new ApiException("cannot delete review");
                }
            }
        }
    }
}
