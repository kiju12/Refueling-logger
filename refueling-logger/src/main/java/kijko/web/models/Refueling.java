package kijko.web.models;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import kijko.web.models.forms.RefuelingForm;

@Entity
public class Refueling {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Vehicle vehicle;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Person person;
	
	private Long meterStatusInThisTime;
	private BigDecimal fuelAmount;
	private BigDecimal priceForLiterInThisTime;
	private LocalDate date;
	private LocalTime time;
	
	public Refueling() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Long getMeterStatusInThisTime() {
		return meterStatusInThisTime;
	}

	public void setMeterStatusInThisTime(Long meterStatusInThisTime) {
		this.meterStatusInThisTime = meterStatusInThisTime;
	}

	public BigDecimal getFuelAmount() {
		return fuelAmount;
	}

	public void setFuelAmount(BigDecimal fuelAmount) {
		this.fuelAmount = fuelAmount;
	}

	public BigDecimal getPriceForLiterInThisTime() {
		return priceForLiterInThisTime;
	}

	public void setPriceForLiterInThisTime(BigDecimal priceForLiterInThisTime) {
		this.priceForLiterInThisTime = priceForLiterInThisTime;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public void removeVehicle() {
		this.vehicle = null;
	}
	
	public void removePerson() {
		this.person = null;
	}
	
	public static Refueling createInstanceFromForm(RefuelingForm form) {
		MathContext mathContext = new MathContext(3, RoundingMode.HALF_UP);
		
		BigDecimal fuelAmount = BigDecimal.valueOf(form.getLitres()).round(mathContext);
		BigDecimal unitPrice = BigDecimal.valueOf(form.getPriceForLiterInThisTime()).round(mathContext);
		
		LocalDate dateOfRefueling = LocalDate.parse(form.getDateInString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		LocalTime timeOfRefueling = LocalTime.parse(form.getTimeInString(), DateTimeFormatter.ofPattern("kk:mm"));
		
		Refueling newRefueling = new Refueling();
		newRefueling.setVehicle(form.getVehicle());
		newRefueling.setPerson(form.getPerson());
		newRefueling.setFuelAmount(fuelAmount);
		newRefueling.setPriceForLiterInThisTime(unitPrice);
		newRefueling.setDate(dateOfRefueling);
		newRefueling.setTime(timeOfRefueling);
		newRefueling.setMeterStatusInThisTime(form.getMeterStatusInThisTime());
		return newRefueling;
	}
}
