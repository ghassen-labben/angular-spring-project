import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrocomponentComponent } from './errocomponent.component';

describe('ErrocomponentComponent', () => {
  let component: ErrocomponentComponent;
  let fixture: ComponentFixture<ErrocomponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ErrocomponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ErrocomponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
