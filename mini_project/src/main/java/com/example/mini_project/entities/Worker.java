package com.example.mini_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Transactional
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Worker extends Personne {

    private String expertise;

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public Worker(String name, String email, String phoneNumber, String expertise,String location,String password,Double daily_price) {
        super(name, email, phoneNumber,location,password);
        this.expertise = expertise;
        this.daily_price=daily_price;
    }

    @ManyToOne
    @JoinColumn(name = "id_service")
    private Job job;


    @OneToOne
    @JoinColumn(name = "Image_id")
    private Image image;
    @Column(name = "daily_price")
    private Double daily_price;
    @OneToMany(mappedBy = "worker")
    private List<Review> reviews;


}
