package com.example.mini_project;

import com.example.mini_project.Repository.ImageRepository;
import com.example.mini_project.Services.*;

import com.example.mini_project.entities.Customer;
import com.example.mini_project.entities.Image;
import com.example.mini_project.entities.Review;
import com.example.mini_project.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiniProjectApplication implements CommandLineRunner {

@Autowired
	CustomerService customerService;
@Autowired
	ReviewService reviewService;
@Autowired
	WorkerService workerService;
@Autowired
	JobService jobService;
@Autowired
ImageService imageService;
	public static void main(String[] args) {
		SpringApplication.run(MiniProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Review review=new Review(3.5,"he does a good work and i think i will hire him the next time");
		//
	}
}
