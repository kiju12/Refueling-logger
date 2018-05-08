package kijko.web.services;

import java.util.List;

import kijko.web.models.Vehicle;

public interface VehicleService {

	Vehicle saveVehicle(Vehicle vehicle);
	Vehicle updateVehicle(Vehicle vehicle);
	Vehicle getVehicleById(Long id);
	List<Vehicle> getAllVehicles();
	void deleteVehicleById(Long id);
	void deleteAllVehicles();
	
}
