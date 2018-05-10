import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { notDigitsValidator } from '../../validators/notDigits';
import { PersonService } from '../../services/person.service';
import { Person, PersonForm } from '../../models/person';

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.css']
})
export class AddPersonComponent implements OnInit {

  addPersonForm: FormGroup;
  personAdded = false;
  personExistsInDb = false;
  lastAddedPerson: Person;

  constructor(private formBuilder: FormBuilder, private personService: PersonService) { }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.addPersonForm = this.formBuilder.group({
      firstName: ['', [Validators.required,
        Validators.minLength(3), Validators.maxLength(32), notDigitsValidator]],
      lastName: ['', [Validators.required,
        Validators.minLength(3), Validators.maxLength(32), notDigitsValidator]]
    });
  }

  addPerson() {
    const personForm: PersonForm = this.createPersonFormInstance();
    this.personService.savePerson(personForm).subscribe((savedPerson: Person) => {
      this.lastAddedPerson = savedPerson;
      this.personExistsInDb = false;
      this.addPersonForm.reset();
      this.personAdded = true;
    }, errorResponse => {
      if (errorResponse.error.errors[0].code === 'exists_error') {
        this.personExistsInDb = true;
      }
    });
  }

  private createPersonFormInstance(): PersonForm {
    return {
      firstName: this.addPersonForm.get('firstName').value,
      lastName: this.addPersonForm.get('lastName').value
    };
  }
}
