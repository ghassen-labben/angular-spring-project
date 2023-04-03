package com.example.mini_project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Personne implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
@Column(name = "email",unique = true)
    private String email;
    private String phoneNumber;

    private String location;
    private String password;
    public Personne() {
    }

    public Personne(String name, String email, String phoneNumber,String location,String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location=location;
        this.password=password;
    }
}
