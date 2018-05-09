package kijko.web.validators;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;

import kijko.web.models.Car;
import kijko.web.models.Person;
import kijko.web.models.Vehicle;
import kijko.web.models.enums.FuelType;
import kijko.web.models.forms.RefuelingForm;

public class RefuelingFormValidatorTest extends ValidatorTest {

	private RefuelingFormValidator valid;
	private Vehicle car;
	private Person person;
	private RefuelingForm form;
	
	@Before
	public void setUp() throws Exception {
		valid = new RefuelingFormValidator();
		car = new Car();
		car.setMark("Mazda");
		car.setModel("MX-5");
		car.setMeterStatus(100L);
		car.setYearOfProduction(1999L);
		
		person = new Person();
		person.setFirstName("Maciej");
		person.setLastName("Kijko");
		
		form = new RefuelingForm<Car>();
		form.setVehicle(car);
		form.setPerson(person);
		form.setDateInString("14.01.1996");
		form.setFuelType(FuelType.LPG);
		form.setLitres(14.5);
		form.setMeterStatusInThisTime(300L);
		form.setPriceForLiterInThisTime(4.95);
		form.setTimeInString("11:10");
		
		errorLog = new BindException(form, "RefuelingForm");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSupports() {
		assertTrue(valid.supports(form.getClass()));
		assertFalse(valid.supports(Random.class));
	}
	
	@Test
	public void shouldPass() {
		valid.validate(form, errorLog);
		assertFalse(errorLog.hasErrors());
	}
	
	@Test
	public void vehicleNullTest() {
		form.setVehicle(null);
		valid.validate(form, errorLog);
		
		assertTrue(hasError("null_error"));
	}
	
	@Test
	public void personNullTest() {
		form.setPerson(null);
		valid.validate(form, errorLog);
		
		assertTrue(hasError("null_error"));
	}
	
	@Test
	public void dateInStringFormatTest() {
		form.setDateInString("1424212412412412");
		valid.validate(form, errorLog);
		
		assertTrue(hasError("wrong_date_format"));
	}
	
	@Test
	public void dateNullTest() {
		form.setDateInString(null);
		valid.validate(form, errorLog);
		
		assertTrue(hasError("null_error"));
	}
	
	@Test
	public void fuelTypeNullTest() {
		form.setFuelType(null);
		valid.validate(form, errorLog);
		
		assertTrue(hasError("null_error"));
	}
	
	@Test
	public void fuelAmountZeroTest() {
		form.setLitres(0);
		valid.validate(form, errorLog);
		
		assertTrue(hasError("litres_zero_of_negative_error"));
	}
	
	@Test
	public void fuelAmountNegativeTest() {
		form.setLitres(-100.0);
		valid.validate(form, errorLog);
		assertTrue(hasError("litres_zero_of_negative_error"));
	}
	
	@Test
	public void meterStatusLessThanVehiclesTest() {
		form.setMeterStatusInThisTime(50L);
		valid.validate(form, errorLog);
		
		assertTrue(hasError("meter_status_less_than_vehicle_error"));
	}
	
	@Test
	public void priceZeroTest() {
		form.setPriceForLiterInThisTime(0);
		valid.validate(form, errorLog);
		
		assertTrue(hasError("price_negative_or_zero_error"));
	}
	
	@Test
	public void priceNegativeTest() {
		form.setPriceForLiterInThisTime(-100.0);
		valid.validate(form, errorLog);
		
		assertTrue(hasError("price_negative_or_zero_error"));
	}
	

	@Test
	public void timeInStringFormatTest() {
		form.setTimeInString("12312312312a");
		valid.validate(form, errorLog);
		
		assertTrue(hasError("wrong_time_format"));
	}
}
