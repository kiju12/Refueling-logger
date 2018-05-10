import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { VehicleService } from '../../services/vehicle.service';
import { VehicleForm, Vehicle } from '../../models/vehicle';

@Component({
  selector: 'app-add-vehicle',
  templateUrl: './add-vehicle.component.html',
  styleUrls: ['./add-vehicle.component.css']
})
export class AddVehicleComponent implements OnInit {

  addVehicleForm: FormGroup;
  vehicleType: string = null;
  lastAddedVehicle: Vehicle;
  vehicleAdded = false;

  constructor(private formBuilder: FormBuilder, private vehicleService: VehicleService) { }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.addVehicleForm = this.formBuilder.group({
      mark: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(32)]],
      model: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(32)]],
      yearOfProduction: [0, [Validators.required, Validators.min(1)]],
      meterStatus: [0, [Validators.required, Validators.min(1)]]
    });
  }

  setVehicleType(vehicleType: string) {
    this.vehicleType = vehicleType;
  }

  addVehicle() {
    let vehicleForm: VehicleForm = null;
    const vehicleAddedFunc = (savedVehicle: Vehicle) => {
      this.lastAddedVehicle = savedVehicle;
      this.vehicleAdded = true;
      this.addVehicleForm.reset();
      this.vehicleType = null;
    };

    if (this.vehicleType === 'CAR') {
      vehicleForm = this.createVehicleFormInstance('CAR');
      this.vehicleService.saveCar(vehicleForm).subscribe(vehicleAddedFunc);
    } else if (this.vehicleType === 'MOTORBIKE') {
      vehicleForm = this.createVehicleFormInstance('MOTORBIKE');
      this.vehicleService.saveMotorbike(vehicleForm).subscribe((savedVehicle: Vehicle) => {
        this.lastAddedVehicle = savedVehicle;
        this.vehicleAdded = true;
        this.addVehicleForm.reset();
        this.vehicleType = null;
      });
    }
  }

  createVehicleFormInstance(vehicleTypeParam: string): VehicleForm {
    return {
      mark: this.addVehicleForm.get('mark').value,
      model: this.addVehicleForm.get('model').value,
      yearOfProduction: this.addVehicleForm.get('yearOfProduction').value,
      meterStatus: this.addVehicleForm.get('meterStatus').value,
      vehicleType: vehicleTypeParam
    };
  }
}
