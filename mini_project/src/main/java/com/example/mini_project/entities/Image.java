package com.example.mini_project.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@Setter
@Table(name = "image_table")
public class Image {

    public Image() {
    }

    public Image(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private MultipartFile imageFile;
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;



    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
    @Column(name = "picByte", length = 1000000)
    private byte[] picByte;


}