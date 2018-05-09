package kijko.web.validators;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;

import kijko.web.models.enums.VehicleType;
import kijko.web.models.forms.VehicleForm;

public class VehicleFormValidatorTest extends ValidatorTest {

	private VehicleFormValidator validator;
	private VehicleForm form;
	
	@Before
	public void setUp() throws Exception {
		form = new VehicleForm();
		form.setMark("Mazda");
		form.setModel("Mx-5");
		form.setMeterStatus(100L);
		form.setVehicleType(VehicleType.CAR);
		form.setYearOfProduction(1999L);
		
		errorLog = new BindException(form, "VehicleForm");
		validator = new VehicleFormValidator();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void supportTest() {
		assertTrue(validator.supports(form.getClass()));
		assertFalse(validator.supports(Random.class));
	}
	
	@Test
	public void shouldPassTest() {
		validator.validate(form, errorLog);
		assertFalse(errorLog.hasErrors());
	}
	
	@Test
	public void tooShortTest() {
		form.setMark(generateStringOfLength(1));
		validator.validate(form, errorLog);
		assertTrue(hasError("too_short_error"));
	}
	
	@Test
	public void tooShortTestModel() {
		form.setModel(generateStringOfLength(1));
		validator.validate(form, errorLog);
		assertTrue(hasError("too_short_error"));
	}
	
	@Test
	public void tooLongTestMark() {
		form.setMark(generateStringOfLength(33));
		validator.validate(form, errorLog);
		assertTrue(hasError("too_long_error"));
	}
	
	@Test
	public void tooLongTestModel() {
		form.setModel(generateStringOfLength(33));
		validator.validate(form, errorLog);
		assertTrue(hasError("too_long_error"));
	}
	
	@Test
	public void nullErrors() {
		form.setModel(null);
		form.setMeterStatus(null);
		validator.validate(form, errorLog);
		
		assertTrue(hasError("null_error"));
	}
	
	@Test
	public void productionYearTest1() {
		form.setYearOfProduction(-10L);
		validator.validate(form, errorLog);
		
		assertTrue(hasError("negative_number_error"));
	}
	
	@Test
	public void meterStatusTest() {
		form.setMeterStatus(-10L);
		validator.validate(form, errorLog);
		
		assertTrue(hasError("negative_number_error"));
	}
	
	@Test
	public void vehicleTypeTest() {
		form.setVehicleType(null);
		validator.validate(form, errorLog);
		
		assertTrue(hasError("null_error"));
	}

}
