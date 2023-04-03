package com.example.mini_project.Repository;

import com.example.mini_project.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByCustomerIdAndReadFalse(Integer customerId);
}
