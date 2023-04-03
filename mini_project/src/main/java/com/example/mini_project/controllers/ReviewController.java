package com.example.mini_project.controllers;

import com.example.mini_project.Services.ReviewService;
import com.example.mini_project.entities.Review;
import com.example.mini_project.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
@Autowired
private ReviewService reviewService;
    @PostMapping("/new")
    public Review createReview(@RequestParam String comment, @RequestParam double rating, @RequestParam Integer idWorker, @RequestParam Integer idCustomer)
    {
        Review rev=new Review(rating,comment);
        return reviewService.createReview(rev,idCustomer,idWorker);
    }
@GetMapping("/{workerId}")
public List<Review> getReviewsByWorkerId(@PathVariable("workerId") Integer workerId) {
    Worker worker = new Worker();
    worker.setId(workerId);
    return reviewService.getReviewsByWorker(worker);
}
}
