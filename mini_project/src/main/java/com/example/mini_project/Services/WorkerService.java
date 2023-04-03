package com.example.mini_project.Services;

import com.example.mini_project.Exceptions.ResourceNotFoundException;
import com.example.mini_project.Repository.JobRepository;
import com.example.mini_project.Repository.WorkerRepository;
import com.example.mini_project.entities.Image;
import com.example.mini_project.entities.Job;
import com.example.mini_project.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private JobService jobService;

    @Autowired
    private ImageService imageService;

    public List<Worker> getAllWorkers() {
        List<Worker> workers = workerRepository.findAll();
        for (int i = 0; i < workers.size(); i++) {
            Image image = workers.get(i).getImage();
            if (image != null) {
                byte[] compressedBytes = image.getPicByte();
                byte[] uncompressedBytes = imageService.decompressBytes(compressedBytes);
                image.setPicByte(uncompressedBytes);
            }
            workers.get(i).setImage(image);
        }
return workers;
    }


    public Integer getWorkDone(Integer id)
    {
        return workerRepository.getWorkDone(id);
    }
    public Worker getWorkerById(Integer id) {
        Worker wrk=workerRepository.findById(id).orElse(null);
        return wrk;
    }
    public Worker findWorkerByemail(String email) {
        Worker wrk=workerRepository.findWorkerByEmail(email);
         Image img=wrk.getImage();
        if (img != null) {
            byte[] compressedBytes = img.getPicByte();
            byte[] uncompressedBytes = imageService.decompressBytes(compressedBytes);
            img.setPicByte(uncompressedBytes);
        }
        wrk.setImage(img);
        return wrk;
    }

    public Worker createWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public Worker updateWorker(Integer id, Worker workerDetails) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Worker", "id", id));

        worker.setName(workerDetails.getName());
        worker.setExpertise(workerDetails.getExpertise());
        worker.setPhoneNumber(workerDetails.getPhoneNumber());
        worker.setJob(workerDetails.getJob());

        return workerRepository.save(worker);
    }
    public List<Worker> findWorkersByJobIdAndLocation(Integer jobId, String location) {
      List<Worker> wrkrs=workerRepository.findWorkersByJobIdAndLocation(jobId, location);
        for (int i = 0; i < wrkrs.size(); i++) {
            Image image = wrkrs.get(i).getImage();
            if (image != null) {
                byte[] compressedBytes = image.getPicByte();
                byte[] uncompressedBytes = imageService.decompressBytes(compressedBytes);
                image.setPicByte(uncompressedBytes);
            }
            wrkrs.get(i).setImage(image);
        }
    return wrkrs;}



}
