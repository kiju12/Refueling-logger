import { TestBed, inject } from '@angular/core/testing';

import { RefuelingService } from './refueling.service';

describe('RefuelingService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RefuelingService]
    });
  });

  it('should be created', inject([RefuelingService], (service: RefuelingService) => {
    expect(service).toBeTruthy();
  }));
});
