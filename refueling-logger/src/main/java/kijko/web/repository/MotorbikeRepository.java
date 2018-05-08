package kijko.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kijko.web.models.Motorbike;

@Repository
public interface MotorbikeRepository extends JpaRepository<Motorbike, Long> {

}
