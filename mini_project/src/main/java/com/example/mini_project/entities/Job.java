package com.example.mini_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "services")
public class Job  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NonNull
    private String name;


    @Column(name = "description")
    @NonNull
    private String description;

    @Column(name = "avg_price")
    @NonNull
    private double price;

    @OneToOne
    @JoinColumn(name = "Image_id")
    private Image image;
    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private List<Worker> workers;



}
