package com.example.mini_project.Repository;

import com.example.mini_project.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Long> {
    Optional<Image> findByName(String name);


}
