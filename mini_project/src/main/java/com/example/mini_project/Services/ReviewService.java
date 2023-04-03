package com.example.mini_project.Services;

import com.example.mini_project.Exceptions.ResourceNotFoundException;
import com.example.mini_project.Repository.CustomerRepository;
import com.example.mini_project.Repository.ReviewRepository;
import com.example.mini_project.Repository.WorkerRepository;
import com.example.mini_project.entities.Customer;
import com.example.mini_project.entities.Review;
import com.example.mini_project.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
@Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WorkerRepository workerRepository;

@Autowired
ImageService imageService;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Integer reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));
    }

    public Review createReview(Review review, Integer customerId, Integer workerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new ResourceNotFoundException("Worker", "id", workerId));
if(reviewRepository.findNumberOfreviews(customer.getId(),worker.getId())!=0)
{
throw new RuntimeException("el customer deja 3ta review");
}

    review.setCustomer(customer);
    review.setWorker(worker);
    return reviewRepository.save(review);


    }

    public Review updateReview(Integer reviewId, Review reviewDetails) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));
        review.setRating(reviewDetails.getRating());
        review.setComment(reviewDetails.getComment());
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));
        reviewRepository.delete(review);
    }

    public List<Review> getReviewsByCustomer(Integer customerId) {

            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
            return reviewRepository.findByCustomer(customer);

    }

    public List<Review> getReviewsByWorker(Worker worker) {

        List<Review> rv=reviewRepository.findByWorker(worker);
        for(Review r:rv)
        {

            r.getCustomer().getImage().setPicByte(imageService.decompressBytes(r.getCustomer().getImage().getPicByte()) );
            r.getWorker().getImage().setPicByte(imageService.decompressBytes(r.getWorker().getImage().getPicByte()) );

        }
        return rv;
    }
}
