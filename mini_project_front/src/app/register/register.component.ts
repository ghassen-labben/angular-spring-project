import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpEventType, HttpHeaders } from '@angular/common/http';
import { JobServiceService } from '../Services/job-service.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Worker } from '../classes/worker'
import { WorkerService } from '../Services/worker.service';
import { Customer } from '../classes/customer';
import { CustomerService } from '../Services/customer.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  constructor(private httpClient: HttpClient, private jobservice: JobServiceService, private formBuilder: FormBuilder, private workerService: WorkerService, private customerservice: CustomerService, private router: Router) { }
  ngOnInit(): void {
    this.jobservice.getJobs().subscribe((data) => {
      console.log(data);
      this.services = data;
    });
    this.WorkerForm = this.formBuilder.group(
      {
        name: ['jhon'],
        password: ['*******'],
        email: ['****@gmail****'],
        idService: [0],
        expertise: ['sdqsdsq'],
        location: ['tunis'],
        phoneNumber: ['95102369'],
        daily_price: [0.0],
        pro: [false]
      }
    )
  }
  WorkerForm!: FormGroup;

  image!: File;
  onSubmit() {
    if (this.WorkerForm.value.pro) {
      const worker = new Worker(this.WorkerForm.value.name, this.WorkerForm.value.phoneNumber, this.WorkerForm.value.email, this.WorkerForm.value.location, this.WorkerForm.value.expertise, this.WorkerForm.value.daily_price, this.WorkerForm.value.password, this.WorkerForm.value.idService, []);
      this.workerService.createWorker(worker, this.image).subscribe((data) => {
        console.log(data);
        this.router.navigate(['/']);

      }, (error) => {
        console.log(error);
      });


    } else {
      const customer = new Customer(this.WorkerForm.value.name, this.WorkerForm.value.phoneNumber, this.WorkerForm.value.email, this.WorkerForm.value.location, this.WorkerForm.value.password);
      this.customerservice.createWorker(customer, this.image).subscribe((data) => {
        console.log(data)
        this.router.navigate(['/']);
      }, (error) => {
      }

      );
    }
    this.router.navigate(['/login'])
  }






  services!: any;
  pro = false;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message!: string;
  imageName: any;

  states = ["Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Mahdia", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan"]

  //Gets called when the user selects an image
  public onFileChanged(event: any) {
    //Select File
    this.image = event.target.files[0];
    console.log(this.image, this.image.name);

  }



  //Gets called when the user clicks on retieve image button to get the image from back end
  getImage() {
    //Make a call to Sprinf Boot to get the Image Bytes.
    this.httpClient.get('http://localhost:8084/image/get/' + this.imageName)
      .subscribe(
        res => {
          this.retrieveResonse = res;
          this.base64Data = this.retrieveResonse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        }
      );
  }

}
