package com.example.mini_project.Repository;

import com.example.mini_project.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    List<Appointment> findByWorkerId(Integer workerId);
}
