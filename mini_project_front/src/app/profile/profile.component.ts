import { AfterViewInit, Component, OnInit } from '@angular/core';
import { WorkerService } from '../Services/worker.service';
import { Worker } from '../classes/worker';
import { ActivatedRoute } from '@angular/router';
import { Review } from '../classes/review';
import { ReviewService } from '../Services/review.service';
import { Customer } from '../classes/customer';
import { data } from 'autoprefixer';
import { Appointment, AppointmentStatus } from '../classes/appointment';
import { AppointmentService } from '../Services/appointment.service';
import { FormBuilder, FormGroup } from '@angular/forms';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, AfterViewInit {
  constructor(private workerservice: WorkerService, private activatedRoute: ActivatedRoute, private reviewservice: ReviewService, private appointmentService: AppointmentService, private formBuilder: FormBuilder) {

  }
  ngAfterViewInit(): void {
    this.workerservice.getWorker(this.idWorker).subscribe(data => {
      console.log(data);

      this.worker = data;
    })
  }
  ngOnInit(): void {
    this.idWorker = this.activatedRoute.snapshot.params['id'];

    console.log(this.reviews);

    this.workerservice.getWorker(this.idWorker).subscribe(data => {
      console.log(data);

      this.worker = data;
    })
    const customer = localStorage.getItem('customer');
    if (customer != null) {
      this.customer = JSON.parse(customer);

    }
    this.reviewservice.getReviewByWorker(this.idWorker).subscribe(data => {
      console.log(data);
      this.reviews = data;

    });


    this.appointmentForm = this.formBuilder.group({
      appointmentDate: Date,
      location: ['Tunis'],
      pricebyday: [100],
      nbdays: [1],
      description: ['']
    });

  }
  public getWorkerService() {
    return this.workerservice;
  }
  appointmentForm!: FormGroup;
  appointment!: Appointment;
  idWorker!: number;
  worker!: Worker;
  customer!: any;
  comment: string = "good";
  modalIsOpen = false;
  reviews!: any;
  openModal() {
    this.modalIsOpen = true;
  }

  closeModal() {
    this.modalIsOpen = false;
  }

  rating = 0;
  idCustomer!: number;
  onSubmit() {
    console.log("hhhhhhhhhhhhh")
    const formData = new FormData();
    formData.append('comment', this.comment);
    formData.append('rating', this.rating.toString());
    formData.append('idWorker', this.idWorker.toString());
    formData.append('idCustomer', this.customer.id);

    console.log(this.customer);
    this.reviewservice.createReview(formData).subscribe(data => console.log(data));
  }

  onSubmit2() {
    this.appointment = new Appointment(this.worker, this.customer, this.appointmentForm.value.appointmentDate, this.appointmentForm.value.location, AppointmentStatus.pending, this.appointmentForm.value.pricebyday * this.appointmentForm.value.nbdays, this.appointmentForm.value.description);
    this.appointmentService.addAppointment(this.appointment)
      .subscribe(data => console.log(data), error => console.log(error));
  }
}
