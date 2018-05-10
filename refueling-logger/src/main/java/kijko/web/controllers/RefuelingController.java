package kijko.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kijko.web.models.Car;
import kijko.web.models.Motorbike;
import kijko.web.models.Refueling;
import kijko.web.models.forms.RefuelingForm;
import kijko.web.services.RefuelingService;
import kijko.web.validators.RefuelingFormValidator;

@RestController
@RequestMapping("/refuelings")
public class RefuelingController {

	@Autowired
	private RefuelingService refuelService;
	
	@Autowired
	private RefuelingFormValidator validator;
	
	@PostMapping("/car")
	public Refueling saveRefuelingForCar(@RequestBody RefuelingForm<Car> form,
			BindingResult validationErrors) throws BindException {
		validator.validate(form, validationErrors);
		
		if (validationErrors.hasErrors())
			throw new BindException(validationErrors);
		
		Refueling newRefueling = Refueling.createInstanceFromForm(form);
		
		return refuelService.saveRefueling(newRefueling);
	}
	
	@PostMapping("/motorbike")
	public Refueling saveRefuelingForMotorbike(@RequestBody RefuelingForm<Motorbike> form,
			BindingResult validationErrors) throws BindException {
		validator.validate(form, validationErrors);
		
		if (validationErrors.hasErrors())
			throw new BindException(validationErrors);
		
		Refueling newRefueling = Refueling.createInstanceFromForm(form);
		
		return refuelService.saveRefueling(newRefueling);
	}
	
	@PutMapping("/car/{id}")
	public Refueling updateCarRefueling(@RequestBody RefuelingForm<Car> form, @PathVariable("id") Long id,
			BindingResult validationErrors) throws BindException {
		validator.validate(form, validationErrors);
		System.out.println(form.getTimeInString());
		if (validationErrors.hasErrors())
			throw new BindException(validationErrors);
		
		Refueling updatedRefueling = Refueling.createInstanceFromForm(form);
		updatedRefueling.setId(id);
		
		return refuelService.updateRefueling(updatedRefueling);
	}
	
	@PutMapping("/motorbike/{id}")
	public Refueling updateMotorbikeRefueling(@RequestBody RefuelingForm<Motorbike> form, @PathVariable("id") Long id,
			BindingResult validationErrors) throws BindException {
		validator.validate(form, validationErrors);
		
		if (validationErrors.hasErrors())
			throw new BindException(validationErrors);
		
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
