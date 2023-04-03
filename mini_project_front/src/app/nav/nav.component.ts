import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../Services/customer.service';
import { Notification } from '../classes/notification';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit, AfterViewInit {

  ngOnInit(): void {
    const customer = localStorage.getItem('customer');

    if (customer != null) {
      this.customer = JSON.parse(customer);

      this.customerservice.getUnreadNotifications(this.customer.id).subscribe((data) => {
        this.notifications = data;
        console.log(this.notifications);
        console.log(this.notifications[1].appointment.finalPrice);

      })
    } else {
      console.log("Customer not found.");
    }



  }
  constructor(private customerservice: CustomerService) { }
  ngAfterViewInit(): void {
    const worker = localStorage.getItem('myWorker');

    if (worker != null) {
      this.worker = JSON.parse(worker);
    }
  }

  id: any;
  isOpen = false;
  isOpen2 = false;
  worker: any = null;
  getItem(key: string): any {
    const value = localStorage.getItem(key);
    return value ? JSON.parse(value) : null;
  }
  customer: any;
  notifications!: Notification[];
  sigout() {
    if (this.customer != null)
      localStorage.setItem('customer', '');
    else
      localStorage.setItem('myWorker', '');

  }
  openNot() {
    this.customerservice.getUnreadNotifications(this.customer.id).subscribe((data) => {
      this.notifications = data;
    })
  }
  viewNot() {
    this.isOpen2 = !this.isOpen2;

  }
}
