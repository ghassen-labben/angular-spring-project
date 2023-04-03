import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Worker } from '../classes/worker'
import { Review } from '../classes/review';

@Injectable({
  providedIn: 'root'
})
export class WorkerService {
  private readonly apiUrl = 'http://localhost:8084/workers';

  constructor(private http: HttpClient) { }

  createWorker(worker: any, imageFile: File) {
    const formData = new FormData();
    formData.append('name', String(worker.name));
    formData.append('email', worker.email);
    formData.append('phoneNumber', worker.phoneNumber);
    formData.append('expertise', worker.expertise);
    formData.append('location', worker.location);
    formData.append('password', String(worker.password));
    formData.append('daily_price', String(worker.daily_price));
    formData.append('imageFile', imageFile, imageFile.name);
    formData.append('serviceId', String(worker.serviceId));

    return this.http.post(this.apiUrl + '/new', formData).pipe(
      catchError((error) => {
        console.error(error);
        return throwError('Error inserting data');
      })
    );
  }
  calculateReview(reviews: Review[]) {
    let s = 0;
    for (let i = 0; i < reviews.length; i++) {
      s += reviews[i].rating;
    }
    if (reviews.length != 0)
      return s / reviews.length;
    else
      return 0;
  }
  authenticateWorker(email: string, password: string) {
    const url = `${this.apiUrl}/${email}/${password}`;
    return this.http.get(url).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError('Resource not found');
        } else if (error.status === 401) {
          return throwError('Invalid password');
        } else {
          return throwError('An error occurred');
        }
      })
    );
  }



  getAllWorker(): Observable<Worker[]> {
    return this.http.get<Worker[]>(this.apiUrl).pipe(catchError(this.handleError));
  }
  getWorker(id: number): Observable<Worker> {
    return this.http.get<Worker>(`${this.apiUrl}/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  addWorker(worker: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<any>(`${this.apiUrl}/new`, worker, { headers })
      .pipe(
        catchError(this.handleError)
      );
  }
  getWorkerByjob(id: number): Observable<Worker[]> {
    return this.http.get<Worker[]>(`${this.apiUrl}/getByjob/${id}`).pipe(
      catchError(this.handleError)
    );
  }
  getWorkerByjobAndLocation(id: number, location: String): Observable<Worker[]> {
    return this.http.get<Worker[]>(`${this.apiUrl}/WorkersByJobLocation/${id}/${location}`).pipe(
      catchError(this.handleError)
    );
  }
  updateJob(id: number, job: Worker): Observable<Worker> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Worker>(`${this.apiUrl}/${id}`, job, { headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteWorker(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError(
      'Something bad happened; please try again later.');
  }

}
