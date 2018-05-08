package kijko.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kijko.web.models.Refueling;
import kijko.web.models.Vehicle;
import kijko.web.repository.RefuelingRepository;
import kijko.web.services.RefuelingService;
import kijko.web.services.VehicleService;

@Service
public class RefuelingServiceImpl implements RefuelingService {

	@Autowired
	private RefuelingRepository refuelRepo;
	
	@Autowired
	private VehicleService vehService;
	
	@Override
	public Refueling saveRefueling(Refueling refueling) {
		updateVehicleMeterStatus(refueling);
		return refuelRepo.save(refueling);
	}

	@Override
	public Refueling updateRefueling(Refueling refueling) {
		updateVehicleMeterStatus(refueling);
		return refuelRepo.save(refueling);
	}

	private void updateVehicleMeterStatus(Refueling refueling) {
		if (isDetached(refueling))
			return;
		
		Vehicle vehicle = refueling.getVehicle();
		vehicle.setMeterStatus(refueling.getMeterStatusInThisTime());
		vehicle = vehService.updateVehicle(vehicle);
		
		refueling.setVehicle(vehicle);
	}
	
	private boolean isDetached(Refueling refueling) {
		return refueling.getVehicle() == null && refueling.getPerson() == null;
	}
	
	@Override
	public void deleteRefuelingById(Long id) {
		Refueling refuelingToDelete = getRefuelingById(id);
		detachPersonAndVehicleFromRefueling(refuelingToDelete);
		
		refuelRepo.deleteById(id);
	}

	private void detachPersonAndVehicleFromRefueling(Refueling refuelingToDelete) {
		refuelingToDelete.removeVehicle();
		refuelingToDelete.removePerson();
		updateRefueling(refuelingToDelete);
	}
	
	@Override
	public List<Refueling> getAllRefuelings() {
		return refuelRepo.findAll();
	}

	@Override
	public Refueling getRefuelingById(Long id) {
		return refuelRepo.findById(id).get();
	}

	@Override
	public void deleteAll() {
		getAllRefuelings().forEach(refueling -> detachPersonAndVehicleFromRefueling(refueling));
		refuelRepo.deleteAll();
	}
}
