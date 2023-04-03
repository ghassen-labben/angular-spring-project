package com.example.mini_project.Repository;

import com.example.mini_project.entities.Worker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkerRepository extends PersonneRepository<Worker> {
    @Query("SELECT w FROM Worker w JOIN w.job j JOIN w.image i WHERE j.id = :jobId AND w.location = :location")
    List<Worker> findWorkersByJobIdAndLocation(@Param("jobId") Integer jobId, @Param("location") String location);
    List<Worker> findAllByJobId(Integer jobId);


    @Query("SELECT w FROM Worker w JOIN w.job j JOIN w.image i WHERE w.email = :email ")
    Worker findWorkerByEmail(@Param("email") String email);

    @Query("SELECT count(a) FROM Appointment a JOIN a.worker w JOIN a.customer c WHERE w.id = :WorkerId ")
    Integer getWorkDone(@Param("WorkerId") Integer WorkerId);

}
