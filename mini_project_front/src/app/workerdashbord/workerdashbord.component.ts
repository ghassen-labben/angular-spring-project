import { Component, OnInit } from '@angular/core';
import { AppointmentService } from '../Services/appointment.service';
import { Appointment, AppointmentStatus } from '../classes/appointment';
import { ReviewService } from '../Services/review.service';
import { WorkerService } from '../Services/worker.service';

@Component({
  selector: 'app-workerdashbord',
  templateUrl: './workerdashbord.component.html',
  styleUrls: ['./workerdashbord.component.scss']
})
export class WorkerdashbordComponent implements OnInit {
  constructor(private appointmentService: AppointmentService, private workerService: WorkerService) { }
  ngOnInit(): void {
    const worker = localStorage.getItem('myWorker');
    if (worker != null) {
      this.worker = JSON.parse(worker);
    }
    this.appointmentService.getAppointmentsByWorker(this.worker.id)
      .subscribe(appointments => {
        this.appointments = appointments;
        console.log(this.appointments);
      });
  }
  updateStatus(appointmentId: number) {
    this.appointmentService.updateAppointmentStatus(appointmentId, AppointmentStatus.accepted)
      .subscribe(updatedAppointment => {
        console.log(updatedAppointment.statues);
      }, error => {
        console.log(error);
      });
  }

  public getWorkerService() {
    return this.workerService;
  }

  appointments: any;
  worker: any;
}
