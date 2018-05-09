import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRefuelingComponent } from './add-refueling.component';

describe('AddRefuelingComponent', () => {
  let component: AddRefuelingComponent;
  let fixture: ComponentFixture<AddRefuelingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddRefuelingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddRefuelingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
