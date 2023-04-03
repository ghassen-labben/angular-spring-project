import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private httpclient: HttpClient) { }
  url = "http://localhost:8084/reviews"

  createReview(review: any) {

    return this.httpclient.post(this.url + '/new', review);
  }
  getReviewByWorker(workerid: number) {
    return this.httpclient.get(this.url + '/' + workerid);
  }

}
