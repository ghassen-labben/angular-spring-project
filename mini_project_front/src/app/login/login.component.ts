import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { WorkerService } from '../Services/worker.service';
import { CustomerService } from '../Services/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  ngOnInit(): void {
    this.LoginForm = this.formBuilder.group(
      {
        'email': [""],
        'password': ["*****"],
        'pro': false

      }

    )
  }
  worker: any;
  errorMessage!: string;

  constructor(private workerService: WorkerService, private formBuilder: FormBuilder, private router: Router, private customerservice: CustomerService) { }

  authenticateWorker(email: string, password: string) {
    this.workerService.authenticateWorker(email, password).subscribe(
      (response) => {
        this.worker = response;
        this.errorMessage = '';
        const workerString = JSON.stringify(this.worker);
        localStorage.setItem('myWorker', workerString);
        this.router.navigate(['/']);

      },
      (error) => {
        console.log(error);
        if (error === 'Resource not found') {
          this.errorMessage = 'Invalid password';
        }
        else {
          this.errorMessage = 'Email Not Found';
        }
        this.worker = null;
      }
    );
  }
  authenticateCustomer(email: string, password: string) {
    this.customerservice.authenticateCustomer(email, password).subscribe(
      (response) => {
        this.customer = response;
        this.errorMessage = '';
        const customerString = JSON.stringify(this.customer);
        localStorage.setItem('customer', customerString);
        this.router.navigate(['/']);

      },
      (error) => {
        console.log(error);
        if (error === 'Resource not found') {
          this.errorMessage = 'Invalid password';
        }
        else {
          this.errorMessage = 'Email Not Found';
        }
        this.worker = null;
      }
    );
  }
  customer: any;
  LoginForm!: FormGroup;
  onSubmit() {
    console.log(this.LoginForm.value.pro)
    if (this.LoginForm.value.pro)
      this.authenticateWorker(this.LoginForm.value.email, this.LoginForm.value.password);
    else
      this.authenticateCustomer(this.LoginForm.value.email, this.LoginForm.value.password);
  }
}
