import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Worker } from '../classes/worker';

@Injectable({
  providedIn: 'root'
})
export class MyService {
  private url = 'http://localhost:8084/workers';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(private http: HttpClient) { }

  getData() {
    return this.http.get(this.url);
  }

  postData(data: any) {
    return this.http.post(this.url, data);
  }

  addWorker(worker: Worker): Observable<Worker> {
    return this.http.post<Worker>(this.url, worker, this.httpOptions);
  }
}
