package com.example.mini_project.Repository;

import com.example.mini_project.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Integer> {
}
