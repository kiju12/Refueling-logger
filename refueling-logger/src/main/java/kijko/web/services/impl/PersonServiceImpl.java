package kijko.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kijko.web.models.Person;
import kijko.web.repository.PersonRepository;
import kijko.web.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepo;
	
	@Override
	public Person savePerson(Person person) {
		return personRepo.save(person);
	}

	@Override
	public Person getPersonById(Long id) {
		return personRepo.findById(id).get();
	}

	@Override
	public List<Person> getAllPersons() {
		return personRepo.findAll();
	}

	@Override
	public void deleteAll() {
		personRepo.deleteAll();
	}

}
