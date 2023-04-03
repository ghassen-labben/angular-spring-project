package com.example.mini_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.io.Serializable;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    @JsonIgnore
    private Worker worker;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NonNull
    private Double rating;
    @NonNull
    private String comment;

    @Override
    public String toString() {
return " "+getId()+"   "+getRating()+" "+getCustomer()+" "+getWorker();
    }
}
