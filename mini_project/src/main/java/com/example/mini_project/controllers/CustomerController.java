package com.example.mini_project.controllers;

import com.example.mini_project.Exceptions.InvalidPasswordException;
import com.example.mini_project.Exceptions.ResourceNotFoundException;
import com.example.mini_project.Services.CustomerService;
import com.example.mini_project.Services.ImageService;
import com.example.mini_project.Services.NotificationService;
import com.example.mini_project.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
@Autowired
private ImageService imageService;
@Autowired
private CustomerService customerService;

    @Autowired
    private NotificationService notificationService;
    @PostMapping("/new")
    public ResponseEntity<Worker> createWorker(@RequestParam(value = "name",required = false) String name, @RequestParam(value = "email",required = true) String email, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("location") String location, @RequestParam("expertise") String expertise,
                                                @RequestParam("password") String password, @RequestParam("imageFile") MultipartFile imageFile ) throws IOException {
        Customer cstm = new Customer(name,email,phoneNumber,location,password);
        if (imageFile != null) {
            Image img = new Image(imageFile.getOriginalFilename(), imageFile.getContentType(),
                    imageService.compressBytes(imageFile.getBytes()));

            img=imageService.createImage(img);
            cstm.setImage(img);
        }
customerService.createCustomer(cstm);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{email}/{password}")
    public Customer authenticateCustomer(@PathVariable String email, @PathVariable String password) {
        Customer cst = customerService.findCustomerByemail(email);
        if (cst == null) {
            throw new ResourceNotFoundException("email", "email", email);
        } else if (!cst.getPassword().equals(password)) {
            throw new InvalidPasswordException("password is incorrect");
        } else {
            return cst;
        }
    }
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Integer id)
    {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/{id}/notifications/unread")
    public List<Notification> getUnreadNotifications(@PathVariable Integer id) {
        return notificationService.getUnreadNotifications(id);
    }

}
