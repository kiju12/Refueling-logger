import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Person, PersonForm } from '../models/person';

@Injectable()
export class PersonService {

  apiURL = 'http://localhost:8080/persons';

  private headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });


  constructor(private http: HttpClient) { }

  getAllPersons(): Observable<Person[]> {
    return this.http.get<Person[]>(this.apiURL, { headers: this.headers });
  }

  getPersonById(id: number): Observable<Person> {
    return this.http.get<Person>(`${this.apiURL}/${id}`, { headers: this.headers });
  }

  savePerson(personForm: PersonForm): Observable<Person> {
    return this.http.post<Person>(this.apiURL, personForm, { headers: this.headers });
  }
}
