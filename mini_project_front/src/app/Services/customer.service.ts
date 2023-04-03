import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Notification } from '../classes/notification';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  url = "http://localhost:8084/customers";
  constructor(private http: HttpClient) { }

  createWorker(customer: any, imageFile: File) {
    const formData = new FormData();
    formData.append('name', String(customer.name));
    formData.append('email', customer.email);
    formData.append('phoneNumber', customer.phoneNumber);
    formData.append('expertise', customer.expertise);
    formData.append('location', customer.location);
    formData.append('password', String(customer.password));
    formData.append('imageFile', imageFile, imageFile.name);

    return this.http.post(this.url + '/new', formData);
  }
  authenticateCustomer(email: string, password: string) {
    const url = `${this.url}/${email}/${password}`;
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

  getUnreadNotifications(customerId: number): Observable<Notification[]> {
    const url = `${this.url}/${customerId}/notifications/unread`;
    return this.http.get<Notification[]>(url);
  }
}
