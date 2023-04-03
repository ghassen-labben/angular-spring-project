package com.example.mini_project.controllers;

import com.example.mini_project.Exceptions.InvalidPasswordException;
import com.example.mini_project.Exceptions.ResourceNotFoundException;
import com.example.mini_project.Repository.WorkerRepository;
import com.example.mini_project.Services.ImageService;
import com.example.mini_project.Services.JobService;
import com.example.mini_project.Services.WorkerService;
import com.example.mini_project.entities.Image;
import com.example.mini_project.entities.Job;
import com.example.mini_project.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private JobService jobService;
    @Autowired
    private ImageService imageService;

    @Autowired
    private WorkerRepository workerRepository;


    @GetMapping("/workdone/{id}")
    public Integer Workdoe(@PathVariable Integer id)
    {
        return workerService.getWorkDone(id);
    }

    @GetMapping("/{email}/{password}")
    public Worker authenticateWorker(@PathVariable String email, @PathVariable String password) {
        Worker wrk = workerService.findWorkerByemail(email);
        if (wrk == null) {
            throw new ResourceNotFoundException("email", "email", email);
        } else if (!wrk.getPassword().equals(password)) {
            throw new InvalidPasswordException("password is incorrect");
        } else {
            return wrk;
        }
    }

    @GetMapping("")
    public List<Worker> getAllwWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Worker> getWorkerById(@PathVariable Integer id) {
        Worker worker = workerRepository.findById(id).orElse(null);
        if (worker!=null) {
            worker.getImage().setPicByte(imageService.decompressBytes(worker.getImage().getPicByte()));
            return ResponseEntity.ok(worker);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/WorkersByJobLocation/{id}/{location}")
    public List<Worker> getWorkersByJobAndLocation(@PathVariable Integer id, @PathVariable String location) {
        return workerService.findWorkersByJobIdAndLocation(id, location);
    }

    @PostMapping("/new")
    public  ResponseEntity<Worker> createWorker(@RequestParam(value = "name",required = false) String name, @RequestParam(value = "email",required = true) String email,@RequestParam("phoneNumber") String phoneNumber,@RequestParam("location") String location,@RequestParam("expertise") String expertise,
                                        @RequestParam("daily_price") double daily_price,@RequestParam("password") String password, @RequestParam("imageFile") MultipartFile imageFile,@RequestParam("serviceId") Integer serviceId ) throws IOException {
        Worker wrkr = new Worker(name,email,phoneNumber,expertise,location,password,daily_price);
        if (imageFile != null) {
            Image img = new Image(imageFile.getOriginalFilename(), imageFile.getContentType(),
                    imageService.compressBytes(imageFile.getBytes()));

            img=imageService.createImage(img);
            wrkr.setImage(img);
        }
        Job job=jobService.getJobById(serviceId);
        wrkr.setJob(job);
        workerService.createWorker(wrkr);
        return ResponseEntity.ok().build();
    }

@GetMapping("/getByjob/{jobId}")
public ResponseEntity<List<Worker>> getAllWorkersByJobId(@PathVariable Integer jobId) {
    List<Worker> workers = workerRepository.findAllByJobId(jobId);
    for(Worker wrk:workers)
    {
        Image image=wrk.getImage();
        if(image!=null)
        {
           image.setPicByte(imageService.decompressBytes(image.getPicByte()));

            wrk.setImage(image);
        }
    }
    if (workers != null && !workers.isEmpty()) {
        return ResponseEntity.ok(workers);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

}