import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Worker } from '../classes/worker';
import { JobServiceService } from '../Services/job-service.service';
import { Router } from '@angular/router';
type StateHashMap = { [key: string]: string };

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent implements OnInit {
  worker!: Worker;
  constructor(private formBuilder: FormBuilder, private jobservice: JobServiceService, private router: Router) { }

  SearchForm!: FormGroup;
  ngOnInit(): void {
    this.jobservice.getJobs().subscribe((data) => {
      this.services = data;
    });
    this.SearchForm = this.formBuilder.nonNullable.group({
      location: 'Tunis',
      idService: 0
    })

    const customer = localStorage.getItem('customer');
    if (customer != null)
      this.customer = JSON.parse(customer);
  }
  customer!: any;
  services!: any;
  states: StateHashMap = {
    'Tunis': 'Tunis',
    'Ariana': 'Ariana',
    'Ben Arous': 'Ben Arous',
    'Manouba': 'Manouba',
    'Bizerte': 'Bizerte',
    'Jendouba': 'Jendouba',
    'Kef': 'Kef',
    'Siliana': 'Siliana',
    'Sousse': 'Sousse',
    'Monastir': 'Monastir',
    'Mahdia': 'Mahdia',
    'Sfax': 'Sfax',
    'Medenine': 'Medenine',
    'Tataouine': 'Tataouine',
    'Gafsa': 'Gafsa',
    'Tozeur': 'Tozeur',
    'Gabes': 'Gabes',
    'Kairouan': 'Kairouan',
    'Kasserine': 'Kasserine',
    'Sidi Bouzid': 'Sidi Bouzid',
    'Zaghouan': 'Zaghouan',
  };



  get stateEntries(): [string, string][] {
    return Object.entries(this.states);
  }
  onSubmit() {
    console.log(this.SearchForm.value);
    this.router.navigate(['/search/' + this.SearchForm.value.idService + '/' + this.SearchForm.value.location])
  }
}
