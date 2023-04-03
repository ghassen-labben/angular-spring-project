import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JobServiceService } from '../Services/job-service.service';
import { WorkerService } from '../Services/worker.service';
import { ReviewService } from '../Services/review.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  constructor(private activatedRoute: ActivatedRoute, private httpClient: HttpClient, private jobservice: JobServiceService, private workerservice: WorkerService, private reviewservice: ReviewService) { }
  ngOnInit(): void {
    this.idService = this.activatedRoute.snapshot.params['id'];
    this.location = this.activatedRoute.snapshot.params['location'];
    console.log(this.idService, this.location);
    if (this.location == null) {
      this.workerservice.getWorkerByjob(this.idService).subscribe((data) => {
        this.workers = data;
        console.log(this.workers);
        console.log(this.workers[0])
      })
    }
    else {
      this.workerservice.getWorkerByjobAndLocation(this.idService, this.location).subscribe((data) => {
        this.workers = data;
      })
    }
  }
  states: string[] = ['Tunis', 'Ariana', 'Ben Arous', 'Manouba', 'Nabeul', 'Zaghouan', 'Bizerte', 'Béja', 'Jendouba', 'Kef', 'Siliana', 'Kairouan', 'Kasserine', 'Sidi Bouzid', 'Sousse', 'Monastir', 'Mahdia', 'Sfax', 'Gabès', 'Medenine', 'Tataouine', 'Tozeur', 'Kebili', 'Gafsa'];

  idService!: number;
  location!: String;
  workers!: any;
  public getWorkerService() {
    return this.workerservice;
  }
  public getReviewService() {
    return this.reviewservice;
  }
}
