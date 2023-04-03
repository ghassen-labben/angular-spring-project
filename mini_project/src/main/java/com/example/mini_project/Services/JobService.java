package com.example.mini_project.Services;

import com.example.mini_project.Repository.JobRepository;
import com.example.mini_project.entities.Image;
import com.example.mini_project.entities.Job;
import com.example.mini_project.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.example.mini_project.Exceptions.ResourceNotFoundException;

import java.util.List;


@Service
public class JobService {
    @Autowired
    private  JobRepository jobRepository;

    @Autowired
    private ImageService imageService;


    public List<Job> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();

        // Loop through each job and retrieve the image
        for (Job job : jobs) {
            Image image = job.getImage();
            if (image != null) {
                byte[] compressedBytes = image.getPicByte();
                byte[] uncompressedBytes = imageService.decompressBytes(compressedBytes);
                image.setPicByte(uncompressedBytes);
            }
            job.setImage(image);
        }

        return jobs;
    }

    public Job getJobById(Integer id) {
        return jobRepository.findById(id).orElse(null);
    }




    public Job updateJob(Integer id, Job jobDetails) throws ResourceNotFoundException {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found for this id :: " + id));

        job.setName(jobDetails.getName());
        job.setDescription(jobDetails.getDescription());
        job.setPrice(jobDetails.getPrice());

        return jobRepository.save(job);
    }

    public void deleteJob(Integer id) throws ResourceNotFoundException {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found for this id :: " + id));

        jobRepository.delete(job);
    }
    public List<Worker> getWorkersByJob(Integer id)
    {

        return jobRepository.findById(id).get().getWorkers();
    }
}
