import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkerdashbordComponent } from './workerdashbord.component';

describe('WorkerdashbordComponent', () => {
  let component: WorkerdashbordComponent;
  let fixture: ComponentFixture<WorkerdashbordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WorkerdashbordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WorkerdashbordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
