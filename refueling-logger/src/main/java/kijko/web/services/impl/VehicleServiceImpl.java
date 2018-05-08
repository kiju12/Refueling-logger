package kijko.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kijko.web.models.Vehicle;
import kijko.web.repository.VehicleRepository;
import kijko.web.services.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepo;
	

	@Override
	public Vehicle saveVehicle(Vehicle vehicle) {
		return vehicleRepo.save(vehicle);
	}
	
	@Override
	public Vehicle updateVehicle(Vehicle vehicle) {
		return vehicleRepo.save(vehicle);
	}

	@Override
	public Vehicle getVehicleById(Long id) {
		return vehicleRepo.findById(id).get();
	}

	@Override
	public List<Vehicle> getAllVehicles() {
		return vehicleRepo.findAll();
	}

	@Override
	public void deleteVehicleById(Long id) {
		vehicleRepo.deleteById(id);
	}

	@Override
	public void deleteAllVehicles() {
		vehicleRepo.deleteAll();
	}



}
