import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Refueling, RefuelingForm } from '../models/refueling';

@Injectable()
export class RefuelingService {

  apiURL = 'http://localhost:8080/refuelings';

  private headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) { }

  getAllRefuelings(): Observable<Refueling[]> {
    return this.http.get<Refueling[]>(this.apiURL, { headers: this.headers });
  }

  getRefuelingById(id: number): Observable<Refueling> {
    return this.http.get<Refueling>(`${this.apiURL}/${id}`, { headers: this.headers });
  }

  saveCarRefueling(refuelingForm: RefuelingForm): Observable<Refueling> {
    return this.http.post<Refueling>(`${this.apiURL}/car`, refuelingForm, { headers: this.headers });
  }

  saveMotorbikeRefueling(refuelingForm: RefuelingForm): Observable<Refueling> {
    return this.http.post<Refueling>(`${this.apiURL}/motorbike`, refuelingForm, { headers: this.headers });
  }

  updateCarRefueling(refuelingForm: RefuelingForm, id: number): Observable<Refueling> {
    return this.http.put<Refueling>(`${this.apiURL}/car/${id}`, refuelingForm, {headers: this.headers});
  }

  updateMotorbikeRefueling(refuelingForm: RefuelingForm, id: number): Observable<Refueling> {
    return this.http.put<Refueling>(`${this.apiURL}/motorbike/${id}`, refuelingForm, {headers: this.headers});
  }

  deleteRefuelingById(id: number): Observable<Refueling> {
    return this.http.delete<Refueling>(`${this.apiURL}/${id}`, { headers: this.headers });
  }
}
