package kijko.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kijko.web.models.Vehicle;
import kijko.web.models.VehicleFactory;
import kijko.web.models.forms.VehicleForm;
import kijko.web.services.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping()
	public List<Vehicle> getAllVehicles() {
		return vehicleService.getAllVehicles();
	}
	
	@GetMapping("/{id}")
	public Vehicle getVehicleById(@PathVariable("id") Long id) {
		return vehicleService.getVehicleById(id);
	}
	
	@PostMapping("/cars")
	public Vehicle saveCar(@RequestBody VehicleForm form) {
		// TODO Walidacja formularza - czy typ napewno CAR
		
		Vehicle carToAdd = VehicleFactory.createCarInstance(form);
		return vehicleService.saveVehicle(carToAdd);
	}
	
	@PostMapping("/motorbikes")
	public Vehicle saveMotorbike(@RequestBody VehicleForm form) {
		// TODO Walidacja formularza - czy typ napewno MOTOR
		
		Vehicle motorbikeToAdd = VehicleFactory.createMotorbikeInstance(form);
		return vehicleService.saveVehicle(motorbikeToAdd);
	}
}
