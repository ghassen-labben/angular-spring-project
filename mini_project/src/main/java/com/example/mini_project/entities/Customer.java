package com.example.mini_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers",uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Transactional
public class Customer extends Personne{
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Review> reviews;
    @OneToOne
    @JoinColumn(name = "Image_id")
    private Image image;

    public Customer(String name, String email, String phoneNumber,  String location,String password)
    {
        super(name, email, phoneNumber,location,password);
    }



}
