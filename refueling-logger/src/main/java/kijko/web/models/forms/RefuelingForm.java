package kijko.web.models.forms;

import kijko.web.models.Person;
import kijko.web.models.Vehicle;
import kijko.web.models.enums.FuelType;

public class RefuelingForm<T extends Vehicle> {
	
	private T vehicle;
	private Person person;
	private Long meterStatusInThisTime;
	private double litres;
	private double priceForLiterInThisTime;
	private String dateInString;
	private String timeInString;
	private FuelType fuelType;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(T vehicle) {
		this.vehicle = vehicle;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public double getLitres() {
		return litres;
	}

	public void setLitres(double litres) {
		this.litres = litres;
	}

	public String getDateInString() {
		return dateInString;
	}

	public void setDateInString(String dateInString) {
		this.dateInString = dateInString;
	}

	public String getTimeInString() {
		return timeInString;
	}

	public void setTimeInString(String timeInString) {
		this.timeInString = timeInString;
	}

	public double getPriceForLiterInThisTime() {
		return priceForLiterInThisTime;
	}

	public void setPriceForLiterInThisTime(double priceForLiterInThisTime) {
		this.priceForLiterInThisTime = priceForLiterInThisTime;
	}

	public Long getMeterStatusInThisTime() {
		return meterStatusInThisTime;
	}

	public void setMeterStatusInThisTime(Long meterStatusInThisTime) {
		this.meterStatusInThisTime = meterStatusInThisTime;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

}
