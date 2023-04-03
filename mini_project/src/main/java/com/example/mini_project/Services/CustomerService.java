package com.example.mini_project.Services;

import com.example.mini_project.Exceptions.ResourceNotFoundException;
import com.example.mini_project.Repository.CustomerRepository;
import com.example.mini_project.Repository.ImageRepository;
import com.example.mini_project.entities.Customer;
import com.example.mini_project.entities.Image;
import com.example.mini_project.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ImageService imageService;



    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        Customer cst=customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        Image img=cst.getImage();
        if(img!=null)
        {
            byte[] compressedBytes = img.getPicByte();
            byte[] uncompressedBytes = imageService.decompressBytes(compressedBytes);
            img.setPicByte(uncompressedBytes);
        }
        cst.setImage(img);
        return cst;
    }
    public Customer findCustomerByemail(String email) {
        Customer cst=customerRepository.findCustomerByEmail(email);
        Image img=cst.getImage();
        if (img != null) {
            byte[] compressedBytes = img.getPicByte();
            byte[] uncompressedBytes = imageService.decompressBytes(compressedBytes);
            img.setPicByte(uncompressedBytes);
        }
        cst.setImage(img);
        return cst;
    }
    public Customer createCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Integer id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customer.setName(customerDetails.getName());
        customer.setLocation(customerDetails.getLocation());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        customer.setEmail(customerDetails.getEmail());
        customer.setImage(customerDetails.getImage());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customerRepository.delete(customer);
        } else {
            throw new ResourceNotFoundException("Customer", "id", id);
        }
    }
}
