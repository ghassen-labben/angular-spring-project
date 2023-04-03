package com.example.mini_project.Repository;

import com.example.mini_project.entities.Customer;
import com.example.mini_project.entities.Review;
import com.example.mini_project.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findByCustomer(Customer customer);

    List<Review> findByWorker(Worker worker);


    @Query("SELECT count(r) FROM Review r  WHERE r.customer.id = :customerId and r.worker.id=:id  ")
    Integer findNumberOfreviews(@Param("customerId") Integer customerId,@Param("id") Integer id);
}
