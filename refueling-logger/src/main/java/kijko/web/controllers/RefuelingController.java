package kijko.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kijko.web.models.Refueling;
import kijko.web.models.forms.RefuelingForm;
import kijko.web.services.RefuelingService;

@RestController
@RequestMapping("/refuelings")
public class RefuelingController {

	@Autowired
	private RefuelingService refuelService;
	
	@PostMapping()
	public Refueling saveRefueling(@RequestBody RefuelingForm form) {
		// TODO Walidacja
		Refueling newRefueling = Refueling.createInstanceFromForm(form);
		
		return refuelService.saveRefueling(newRefueling);
	}
	
	@PutMapping("/{id}")
	public Refueling updateRefueling(@RequestBody RefuelingForm form, @PathVariable("id") Long id) {
		// TODO Walidacja
		Refueling updatedRefueling = Refueling.createInstanceFromForm(form);
		updatedRefueling.setId(id);
		
		return refuelService.updateRefueling(updatedRefueling);
	}
	
	@GetMapping()
	public List<Refueling> getAllRefuelings() {
		return refuelService.getAllRefuelings();
	}
	
	@GetMapping("/{id}")
	public Refueling getRefuelingById(@PathVariable("id") Long id) {
		return refuelService.getRefuelingById(id);
	}
	
	@DeleteMapping()
	public void deleteAllRefuelings() {
		refuelService.deleteAll();
	}
	
	@DeleteMapping("/{id}")
	public void deleteRefuelingById(@PathVariable("id") Long id) {
		refuelService.deleteRefuelingById(id);
	}
}
