package kijko.web.models;

import kijko.web.models.forms.VehicleForm;

public abstract class VehicleFactory {

	public static Vehicle createCarInstance(VehicleForm form) {
		Car car = new Car();
		car.setMark(form.getMark());
		car.setModel(form.getModel());
		car.setYearOfProduction(form.getYearOfProduction());
		car.setMeterStatus(form.getMeterStatus());
		
		return car;
	}
	
	public static Vehicle createMotorbikeInstance(VehicleForm form) {
		Motorbike motorbike = new Motorbike();
		motorbike.setMark(form.getMark());
		motorbike.setModel(form.getModel());
		motorbike.setYearOfProduction(form.getYearOfProduction());
		motorbike.setMeterStatus(form.getMeterStatus());
		
		return motorbike;
	}
	
}
