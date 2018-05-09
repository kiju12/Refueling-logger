package kijko.web.models;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import kijko.web.models.enums.VehicleType;

@Entity
@Inheritance
public abstract class Vehicle {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String mark;
	private String model;
	private Long yearOfProduction;
	private Long meterStatus;
	
	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;
	
	public Vehicle() {}
	
	public Vehicle(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Long getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(Long yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public Long getMeterStatus() {
		return meterStatus;
	}

	public void setMeterStatus(Long meterStatus) {
		this.meterStatus = meterStatus;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public Long getId() {
		return id;
	}

}
