import { Worker } from './worker';
import { Customer } from './customer';

export enum AppointmentStatus {
    pending = 'pending',
    accepted = 'accepted',
    refused = 'refused',
    completed = 'completed'
}

export class Appointment {
    worker: Worker;
    customer: Customer;
    date: Date;
    location: string;
    statues: AppointmentStatus;
    finalPrice: number;
    description: string;
    constructor(
        worker: Worker,
        customer: Customer,
        date: Date,
        location: string,
        statues: AppointmentStatus,
        finalPrice: number,
        description: string

    ) {
        this.worker = worker;
        this.customer = customer;
        this.date = date;
        this.location = location;
        this.statues = statues;
        this.finalPrice = finalPrice;
        this.description = description;

    }
}
