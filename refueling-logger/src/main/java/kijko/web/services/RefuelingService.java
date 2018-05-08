package kijko.web.services;

import java.util.List;

import kijko.web.models.Refueling;

public interface RefuelingService {

	Refueling saveRefueling(Refueling refueling);
	Refueling updateRefueling(Refueling refueling);
	void deleteRefuelingById(Long id);
	
	List<Refueling> getAllRefuelings();
	Refueling getRefuelingById(Long id);
	void deleteAll();
}
