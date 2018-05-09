import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RefuelingListComponent } from './refueling-list.component';

describe('RefuelingListComponent', () => {
  let component: RefuelingListComponent;
  let fixture: ComponentFixture<RefuelingListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RefuelingListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RefuelingListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
