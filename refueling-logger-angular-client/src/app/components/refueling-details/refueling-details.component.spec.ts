import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RefuelingDetailsComponent } from './refueling-details.component';

describe('RefuelingDetailsComponent', () => {
  let component: RefuelingDetailsComponent;
  let fixture: ComponentFixture<RefuelingDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RefuelingDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RefuelingDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
