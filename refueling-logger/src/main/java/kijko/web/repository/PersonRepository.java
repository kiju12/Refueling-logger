package kijko.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kijko.web.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
