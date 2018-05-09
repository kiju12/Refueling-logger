package kijko.web;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kijko.web.models.Person;
import kijko.web.models.Refueling;
import kijko.web.models.Vehicle;
import kijko.web.models.VehicleFactory;
import kijko.web.models.enums.FuelType;
import kijko.web.models.enums.VehicleType;
import kijko.web.models.forms.PersonForm;
import kijko.web.models.forms.RefuelingForm;
import kijko.web.models.forms.VehicleForm;
import kijko.web.services.PersonService;
import kijko.web.services.RefuelingService;
import kijko.web.services.VehicleService;

@SpringBootApplication
public class RefuelingLoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefuelingLoggerApplication.class, args);
	}
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private RefuelingService refService;
	
	@Autowired
	private VehicleService vehService;
	
	@PostConstruct
	public void putSomeExampleEntities() {
		PersonForm personForm1 = new PersonForm();
		personForm1.setFirstName("Maciej");
		personForm1.setLastName("Kijko");
		
		PersonForm personForm2 = new PersonForm();
		personForm2.setFirstName("Piotr");
		personForm2.setLastName("Siara");
		
		Person person1 = personService.savePerson(Person.createInstanceFromForm(personForm1));
		Person person2 = personService.savePerson(Person.createInstanceFromForm(personForm2));
		
		VehicleForm vehicleForm1 = new VehicleForm();
		vehicleForm1.setMark("Mazda");
		vehicleForm1.setModel("MX-5");
		vehicleForm1.setMeterStatus(150L);
		vehicleForm1.setVehicleType(VehicleType.CAR);
		vehicleForm1.setYearOfProduction(1999L);
		
		VehicleForm vehicleForm2 = new VehicleForm();
		vehicleForm2.setMark("Honda");
		vehicleForm2.setModel("CRX");
		vehicleForm2.setMeterStatus(100L);
		vehicleForm2.setVehicleType(VehicleType.MOTORBIKE);
		vehicleForm2.setYearOfProduction(2010L);
		
		Vehicle vehicle1 = vehService.saveVehicle(VehicleFactory.createCarInstance(vehicleForm1));
		Vehicle vehicle2 = vehService.saveVehicle(VehicleFactory.createMotorbikeInstance(vehicleForm2));
		
		RefuelingForm refuelingForm1 = new RefuelingForm();
		refuelingForm1.setVehicle(vehicle1);
		refuelingForm1.setPerson(person2);
		refuelingForm1.setDateInString("14.12.1000");
		refuelingForm1.setFuelType(FuelType.LPG);
		refuelingForm1.setLitres(123.3);
		refuelingForm1.setMeterStatusInThisTime(300L);
		refuelingForm1.setPriceForLiterInThisTime(4.5);
		refuelingForm1.setTimeInString("12:10");
		Refueling refueling1 = refService.saveRefueling(Refueling.createInstanceFromForm(refuelingForm1));
		
		RefuelingForm refuelingForm2 = new RefuelingForm();
		refuelingForm2.setVehicle(vehicle2);
		refuelingForm2.setPerson(person1);
		refuelingForm2.setDateInString("16.12.1001");
		refuelingForm2.setFuelType(FuelType.PETROL);
		refuelingForm2.setLitres(123.3);
		refuelingForm2.setMeterStatusInThisTime(301L);
		refuelingForm2.setPriceForLiterInThisTime(2.1);
		refuelingForm2.setTimeInString("12:10");
		Refueling refueling2 = refService.saveRefueling(Refueling.createInstanceFromForm(refuelingForm2));
	}
}
