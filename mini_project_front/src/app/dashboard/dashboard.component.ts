import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Job } from '../classes/job';
import { FormBuilder } from '@angular/forms';
import { JobServiceService } from '../Services/job-service.service';
import { Router } from '@angular/router';
import { WorkerService } from '../Services/worker.service';
import { data } from 'autoprefixer';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  constructor(private httpClient: HttpClient, private formBuilder: FormBuilder, private jobservice: JobServiceService, private router: Router, private workerService: WorkerService) { }
  jobs!: Job[];
  jobImage!: string;
  ngOnInit(): void {
    this.jobservice.getJobs().subscribe((data) => {
      console.log(data);
      this.jobs = data;
    })
    this.workerService.getAllWorker().subscribe((data) => {
      this.workers = data;
    })

  }
  workers: any;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  getImage(imageId: number) {
    this.httpClient.get('http://localhost:8084/image/get/' + imageId)
      .subscribe(
        res => {
          this.retrieveResonse = res;
          this.base64Data = this.retrieveResonse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        }
      );
  }

  job = {
    name: '',
    description: '',
    price: 0
  };
  image!: File;
  onSubmit() {
    const formData = new FormData();
    formData.append('name', this.job.name);
    formData.append('description', this.job.description);
    formData.append('avg_price', this.job.price.toString());
    formData.append('imageFile', this.image);

    this.httpClient.post('http://localhost:8084/services/new', formData).subscribe(
      response => console.log(response),
      error => console.error(error)

    );
    this.router.navigate(['/']);

  }

  onFileSelected(event: any) {
    this.image = event.target.files[0];
  }


}
