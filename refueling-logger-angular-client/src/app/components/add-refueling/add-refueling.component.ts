import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl, AbstractControl } from '@angular/forms';
import { Person } from '../../models/person';
import { Vehicle } from '../../models/vehicle';
import { VehicleService } from '../../services/vehicle.service';
import { PersonService } from '../../services/person.service';
import { RefuelingService } from '../../services/refueling.service';
import { RefuelingForm, FuelType, Refueling } from '../../models/refueling';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-refueling',
  templateUrl: './add-refueling.component.html',
  styleUrls: ['./add-refueling.component.css']
})
export class AddRefuelingComponent implements OnInit {
  updatingMode = false;
  updatingId = -1;

  refuelingAdded = false;
  refuelingUpdated = false;

  addRefuelingForm: FormGroup;
  lastAddedRefueling: Refueling;
  allVehicles: Vehicle[] = [];
  minMeterStatus = 0;
  choosedVehicleType: string;
  meterStatusError = false;
  allPersons: Person[] = [];

  constructor(private formBuilder: FormBuilder, private vehicleService: VehicleService,
    private personService: PersonService, private refuelingService: RefuelingService,
    private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.createForm();
    if (this.isUpdatingMode()) {
      this.vehicleService.getAllVehicles().subscribe(allVehicles => {
        this.allVehicles = allVehicles;

        this.personService.getAllPersons().subscribe(allPersons => {
          this.allPersons = allPersons;

          this.updatingMode = true;
          this.updatingId = +this.route.snapshot.paramMap.get('id');
          this.refuelingService.getRefuelingById(this.updatingId).subscribe(refueling => {
            const vehIndex = this.allVehicles.findIndex(vehicle => refueling.vehicle.id === vehicle.id);
            const perIndex = this.allPersons.findIndex(person => refueling.person.id === person.id);

            this.addRefuelingForm.get('chooseVehicle').setValue(vehIndex);
            this.addRefuelingForm.get('choosePerson').setValue(perIndex);
            this.addRefuelingForm.get('meterStatusInThisTime').setValue(refueling.meterStatusInThisTime);
            this.addRefuelingForm.get('fuelType').setValue(refueling.fuelType);
            this.addRefuelingForm.get('litres').setValue(refueling.fuelAmount);
            this.addRefuelingForm.get('priceForLiterInThisTime').setValue(refueling.priceForLiterInThisTime);
            this.addRefuelingForm.get('date').setValue(refueling.date);
            this.addRefuelingForm.get('time').setValue(refueling.time);
            this.minMeterStatus = refueling.vehicle.meterStatus;
            this.choosedVehicleType = refueling.vehicle.vehicleType;

            this.vehicleSelected();
          });
        });
      });
    } else {
      this.vehicleSelected();
      this.initLists();
    }
  }

  isUpdatingMode() {
    return this.router.url.startsWith('/update');
  }

  getFormControl(str: string): AbstractControl {
    return this.addRefuelingForm.get(str);
  }

  createForm() {
    this.addRefuelingForm = this.formBuilder.group({
      chooseVehicle: [null, [Validators.nullValidator, Validators.required]],
      choosePerson: [null, [Validators.nullValidator, Validators.required]],
      meterStatusInThisTime: [0, [Validators.required, Validators.min(0)]],
      fuelType: [null, [Validators.required, Validators.nullValidator]],
      litres: [0, [Validators.required, Validators.min(0)]],
      priceForLiterInThisTime: [0, [Validators.required, Validators.min(0)]],
      date: [null, [Validators.required, Validators.nullValidator]],
      time: [null, [Validators.required, Validators.nullValidator]]
    });
  }

  initLists() {
    this.vehicleService.getAllVehicles().subscribe(allVehicles => this.allVehicles = allVehicles);
    this.personService.getAllPersons().subscribe(allPersons => this.allPersons = allPersons);
  }

  vehicleSelected() {
    this.addRefuelingForm.get('chooseVehicle').valueChanges.subscribe(selectedIndex => {
      if (selectedIndex === null) {
        this.minMeterStatus = 0;
        this.choosedVehicleType = null;
        return;
      }
      this.choosedVehicleType = this.allVehicles[selectedIndex].vehicleType;
      this.minMeterStatus = this.allVehicles[selectedIndex].meterStatus;
    });
  }

  addRefueling() {
    const fuelTypeInString = this.addRefuelingForm.get('fuelType').value;
    let refuelingForm: RefuelingForm = null;

    if (this.isRefuelingMeterStatusLessThanCarMeterStatus()) {
      this.meterStatusError = true;
      return;
    }

    if (fuelTypeInString === 'PETROL') {
      refuelingForm = this.createRefuelingFormInstance(FuelType.PETROL);
    } else if (fuelTypeInString === 'OIL') {
      refuelingForm = this.createRefuelingFormInstance(FuelType.OIL);
    } else if (fuelTypeInString === 'LPG') {
      refuelingForm = this.createRefuelingFormInstance(FuelType.LPG);
    }

    const refuelingAddedFun = savedRefueling => {
      this.meterStatusError = false;
      this.lastAddedRefueling = savedRefueling;
      this.refuelingAdded = true;
      this.addRefuelingForm.reset();
    };

    const refuelingUpdatedFun = savedRefueling => {
      this.meterStatusError = false;
      this.lastAddedRefueling = savedRefueling;
      this.refuelingUpdated = true;
      this.addRefuelingForm.reset();
    };

    const vehicleIndex = this.addRefuelingForm.get('chooseVehicle').value;
    if (this.choosedVehicleType === 'CAR') {
      if (this.updatingMode) {
        this.refuelingService.updateCarRefueling(refuelingForm, this.updatingId).subscribe(refuelingUpdatedFun);
      } else {
        this.refuelingService.saveCarRefueling(refuelingForm).subscribe(refuelingAddedFun);
      }
    } else if (this.choosedVehicleType === 'MOTORBIKE') {
      if (this.updatingMode) {
        this.refuelingService.updateMotorbikeRefueling(refuelingForm, this.updatingId).subscribe(refuelingUpdatedFun);
      } else {
        this.refuelingService.saveMotorbikeRefueling(refuelingForm).subscribe(refuelingAddedFun);
      }
    }
  }

  isRefuelingMeterStatusLessThanCarMeterStatus(): boolean {
    return this.addRefuelingForm.get('meterStatusInThisTime').value < this.minMeterStatus;
  }

  createRefuelingFormInstance(fuelTypeParam: FuelType): RefuelingForm {
    return {
      vehicle: this.allVehicles[this.addRefuelingForm.get('chooseVehicle').value],
      person: this.allPersons[this.addRefuelingForm.get('choosePerson').value],
      meterStatusInThisTime: this.addRefuelingForm.get('meterStatusInThisTime').value,
      fuelType: fuelTypeParam,
      litres: this.addRefuelingForm.get('litres').value,
      priceForLiterInThisTime: this.addRefuelingForm.get('priceForLiterInThisTime').value,
      dateInString: this.addRefuelingForm.get('date').value.toString(),
      timeInString: this.addRefuelingForm.get('time').value.toString().substring(0, 5)
    };
  }

}
