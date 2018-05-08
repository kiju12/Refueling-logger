package kijko.web.services;

import java.util.List;

import kijko.web.models.Person;

public interface PersonService {

	Person savePerson(Person person);
	Person getPersonById(Long id);
	List<Person> getAllPersons();
	void deleteAll();
}
