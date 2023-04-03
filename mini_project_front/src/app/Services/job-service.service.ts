import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Job } from '../classes/job';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JobServiceService {

  private apiUrl = 'http://localhost:8084/services';

  constructor(private http: HttpClient) { }

  getJobs(): Observable<Job[]> {
    return this.http.get<Job[]>(`${this.apiUrl}`).pipe(
      catchError(this.handleError)
    );

  }



  getJob(id: number): Observable<Job> {
    return this.http.get<Job>(`${this.apiUrl}/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  addJob(job: Job): Observable<Job> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Job>(`${this.apiUrl}`, job, { headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  updateJob(id: number, job: Job): Observable<Job> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Job>(`${this.apiUrl}/${id}`, job, { headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteJob(id: number): Observable<any> {
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
