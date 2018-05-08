package kijko.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kijko.web.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
