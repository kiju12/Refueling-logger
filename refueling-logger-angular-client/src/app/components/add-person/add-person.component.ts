import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { notDigitsValidator } from '../../validators/notDigits';

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.css']
})
export class AddPersonComponent implements OnInit {

  addPersonForm: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.addPersonForm = this.formBuilder.group({
      firstName: ['', [Validators.required,
      Validators.minLength(3),
      Validators.maxLength(32),
        notDigitsValidator]],
      lastName: ['', [Validators.required,
      Validators.minLength(3),
      Validators.maxLength(32),
        notDigitsValidator]]
    });
  }

  addPerson() {
    console.log('Dodano osobę');
  }
}
