package kijko.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kijko.web.models.Refueling;

@Repository
public interface RefuelingRepository extends JpaRepository<Refueling, Long> {

}
