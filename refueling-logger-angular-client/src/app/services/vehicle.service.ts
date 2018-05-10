import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Vehicle, VehicleForm } from '../models/vehicle';

@Injectable()
export class VehicleService {

  apiURL = 'http://localhost:8080/vehicles';

  private headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) { }

  getAllVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(this.apiURL, { headers: this.headers });
  }

  getVehicleById(id: number): Observable<Vehicle> {
    return this.http.get<Vehicle>(`${this.apiURL}/${id}`, { headers: this.headers });
  }

  saveCar(vehicleForm: VehicleForm): Observable<Vehicle> {
    return this.http.post<Vehicle>(`${this.apiURL}/cars`, vehicleForm, { headers: this.headers });
  }

  saveMotorbike(vehicleForm: VehicleForm): Observable<Vehicle> {
    return this.http.post<Vehicle>(`${this.apiURL}/motorbikes`, vehicleForm, { headers: this.headers });
  }
}
