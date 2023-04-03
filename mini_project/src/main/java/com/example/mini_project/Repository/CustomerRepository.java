package com.example.mini_project.Repository;

import com.example.mini_project.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends PersonneRepository<Customer> {
    @Query("SELECT c FROM Customer c JOIN c.image i WHERE c.email = :email ")
    Customer findCustomerByEmail(@Param("email") String email);

}
