package com.example.mini_project.Services;

import com.example.mini_project.Repository.NotificationRepository;
import com.example.mini_project.entities.Appointment;
import com.example.mini_project.entities.Customer;
import com.example.mini_project.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private  NotificationRepository notificationRepository;



    public void createNotification(Integer customerId, String message, Appointment app) {
        Notification notification = new Notification();
        Customer cst=new Customer();
        cst.setId(customerId);
        notification.setCustomer(cst);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notification.setRead(false);
        notification.setAppointment(app);
        notificationRepository.save(notification);
    }

    public List<Notification> getUnreadNotifications(Integer customerId) {
        return notificationRepository.findByCustomerIdAndReadFalse(customerId);
    }
}
