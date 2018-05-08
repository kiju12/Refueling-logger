package kijko.web;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kijko.web.models.Vehicle;
import kijko.web.models.VehicleFactory;
import kijko.web.models.enums.FuelType;
import kijko.web.repository.CarRepository;
import kijko.web.repository.VehicleRepository;

@SpringBootApplication
public class RefuelingLoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefuelingLoggerApplication.class, args);
	}
	
	@PostConstruct
	public void ee() {

	}
}
