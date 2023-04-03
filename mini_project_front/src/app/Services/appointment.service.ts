import { Injectable } from '@angular/core';
import { Appointment, AppointmentStatus } from '../classes/appointment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private baseUrl = 'http://localhost:8084/appointments';


  updateAppointmentStatus(appointmentId: number, newStatus: AppointmentStatus): Observable<Appointment> {
    const url = `${this.baseUrl}/${appointmentId}/status/${newStatus}`;
    return this.http.patch<Appointment>(url, {});
  }
  constructor(private http: HttpClient) { }

  addAppointment(appointment: Appointment): Observable<Appointment> {
    return this.http.post<Appointment>(`${this.baseUrl}`, appointment);
  }
  getAppointmentsByWorker(workerId: number): Observable<Appointment[]> {
    const url = `${this.baseUrl}/${workerId}`;
    return this.http.get<Appointment[]>(url);
  }

}
