package com.example.mini_project.controllers;

import com.example.mini_project.Repository.AppointmentRepository;
import com.example.mini_project.Services.NotificationService;
import com.example.mini_project.entities.Appointment;
import com.example.mini_project.entities.AppointmentStatues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
@Autowired
    AppointmentRepository appointmentRepository;
@Autowired
private     NotificationService notificationService;

    @GetMapping("/{workerId}")
    public List<Appointment> getAppointmentsByWorker(@PathVariable Integer workerId) {
        return appointmentRepository.findByWorkerId(workerId);
    }
    @PostMapping("")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return ResponseEntity.ok(savedAppointment);
    }
    @PatchMapping("/{id}/status/{status}")
    public Appointment updateAppointmentStatus(@PathVariable Integer id, @PathVariable AppointmentStatues status) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        appointment.setStatues(status);
        appointmentRepository.save(appointment);
        notificationService.createNotification(appointment.getCustomer().getId(), "Le statut de votre rendez-vous a été mis à jour.",appointment);
        return appointment;
    }
}
