package kijko.web.models.forms;

import kijko.web.models.enums.FuelType;
import kijko.web.models.enums.VehicleType;

public class VehicleForm {
	private String mark;
	private String model;
	private Long yearOfProduction;
	private Long meterStatus;
	private VehicleType vehicleType;
	private FuelType fuelType;

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

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	
}
