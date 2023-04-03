import { Appointment } from "./appointment";
import { Customer } from "./customer";
import { Worker } from "./worker";

export class Notification {
    id: number;
    customer: Customer;
    message: string;
    public timestamp: Date;
    read: boolean;
    appointment: Appointment;
    constructor(
        id: number,
        customer: Customer,
        message: string,
        timestamp: Date,
        read: boolean,
        appointment: Appointment,
    ) {
        this.id = id;
        this.customer = customer; this.message = message;
        this.timestamp = timestamp;
        this.read = read;
        this.appointment = appointment;
    }
}
