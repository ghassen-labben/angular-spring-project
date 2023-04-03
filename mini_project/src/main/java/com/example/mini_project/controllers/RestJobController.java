package com.example.mini_project.controllers;


import com.example.mini_project.Repository.JobRepository;
import com.example.mini_project.Services.ImageService;
import com.example.mini_project.Services.JobService;
import com.example.mini_project.entities.Image;
import com.example.mini_project.entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/services")
public class RestJobController {

    @Autowired
    private JobService jobService;
    @Autowired
    private ImageService imageService;

    @Autowired
    private JobRepository jobRepository;
    @GetMapping("")
    public List<Job> getAllServices() {
        return jobService.getAllJobs();
    }



    @GetMapping("/{id}")
    public Job getServiceById(@PathVariable Integer id) {
        return jobService.getJobById(id);
    }



    @PutMapping("/{id}")
    public Job updateService(@PathVariable Integer id, @RequestBody Job job) {
        return jobService.updateJob(id, job);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Integer id) {
        jobService.deleteJob(id);
    }

    @PostMapping("/new")
    public  ResponseEntity<?> createJob(@RequestParam("name") String name, @RequestParam("description") String description,
                         @RequestParam("avg_price") double price, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        Job job = new Job(name, description, price);
        if (imageFile != null) {
            Image img = new Image(imageFile.getOriginalFilename(), imageFile.getContentType(),
                    imageService.compressBytes(imageFile.getBytes()));

            img=imageService.createImage(img);
            job.setImage(img);
        }
        jobRepository.save(job);
        return ResponseEntity.ok().build();
    }
}

