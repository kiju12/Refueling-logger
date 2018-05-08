package kijko.web.integral;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kijko.web.services.PersonService;
import kijko.web.services.RefuelingService;
import kijko.web.services.VehicleService;
import kijko.web.models.Car;
import kijko.web.models.Motorbike;
import kijko.web.models.Person;
import kijko.web.models.Refueling;
import kijko.web.models.Vehicle;
import kijko.web.models.enums.FuelType;
import kijko.web.models.enums.VehicleType;
import kijko.web.repository.RefuelingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbIntegralTest {

	@Autowired
	private RefuelingService refuelService;
	
	@Autowired
	private RefuelingRepository refuelRepo;
	
	@Autowired
	private VehicleService vehService;
	
	@Autowired
	private PersonService personService;
	
	private Person person;
	private Vehicle car;
	private Refueling refuel;
	
	@Before
	public void setUp() throws Exception {
		person = new Person();
		person.setFirstName("Maciej");
		person.setLastName("Kijko");
		person = personService.savePerson(person);
		
		car = new Car();
		car.setMark("Mazda");
		car.setModel("Miata");
		car.setFuelType(FuelType.OIL);
		car.setMeterStatus(100L);
		car.setYearOfProduction(1998L);
		car = vehService.saveVehicle(car);
		
		refuel = new Refueling();
		refuel.setVehicle(car);
		refuel.setPerson(person);
		refuel.setDate(LocalDate.of(2018, 1, 3));
		refuel.setTime(LocalTime.of(10, 30));
		refuel.setFuelAmount(BigDecimal.valueOf(20.5));
		refuel.setPriceForLiterInThisTime(BigDecimal.valueOf(4.50));
		refuel.setMeterStatusInThisTime(200L);
		refuel = refuelService.saveRefueling(refuel);
	}

	@After
	public void tearDown() throws Exception {
		refuelRepo.deleteAll();
		vehService.deleteAllVehicles();
		personService.deleteAll();
	}

	@Test
	public void addingTest() {
		assertTrue(refuel.getId() != null);
		
		Vehicle carFromDb = vehService.getVehicleById(car.getId());
		assertTrue(carFromDb.getMeterStatus().intValue() == 200);
		
		assertTrue(refuel.getVehicle().getId() != null);
		assertTrue(refuel.getPerson().getId() != null);
	}
	
	@Test
	public void updatePersonTest() {
		Person person2 = new Person();
		person2.setFirstName("Grzegorz");
		person2.setLastName("Brzęczyszczykiewicz");
		person2 = personService.savePerson(person2);
		
		refuel.setPerson(person2);
		refuel = refuelService.updateRefueling(refuel);
		
		assertEquals(refuel.getPerson().getFirstName(), person2.getFirstName());
	}
	
	@Test
	public void updateVehicleTest() {
		Vehicle motorbike = new Motorbike();
		motorbike.setMark("Honda");
		motorbike.setModel("C");
		motorbike.setFuelType(FuelType.PETROL);
		motorbike.setMeterStatus(100L);
		motorbike.setYearOfProduction(1998L);
		motorbike = vehService.saveVehicle(motorbike);
		
		refuel.setVehicle(motorbike);
		refuel = refuelService.updateRefueling(refuel);
		
		assertEquals(refuel.getVehicle().getVehicleType(), VehicleType.MOTORBIKE);
	}
	
	@Test
	public void deletingTest() {
		Long vehId = refuel.getVehicle().getId();
		Long personId = refuel.getPerson().getId();
		
		refuelService.deleteRefuelingById(refuel.getId());
		
		assertNotNull(vehService.getVehicleById(vehId));
		assertNotNull(personService.getPersonById(personId));
	}
	
	@Test
	public void deleteAllTest() {
		refuelService.deleteAll();
		
		assertTrue(vehService.getAllVehicles().size() != 0);
		assertTrue(personService.getAllPersons().size() != 0);
	}

}
