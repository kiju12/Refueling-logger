package kijko.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kijko.web.models.Person;
import kijko.web.models.forms.PersonForm;
import kijko.web.services.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping()
	public List<Person> getAllPersons() {
		return personService.getAllPersons();
	}
	
	@GetMapping("/{id}")
	public Person getPersonById(@PathVariable("id") Long id) {
		return personService.getPersonById(id);
	}
	
	@PostMapping()
	public Person savePerson(@RequestBody PersonForm form) {
		// TODO Walidacja
		
		Person personToAdd = Person.createInstanceFromForm(form);
		return personService.savePerson(personToAdd);
	}
}
