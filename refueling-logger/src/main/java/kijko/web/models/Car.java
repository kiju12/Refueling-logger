package kijko.web.models;

import javax.persistence.Entity;

import kijko.web.models.enums.VehicleType;

@Entity
public class Car extends Vehicle {

	public Car() {
		super(VehicleType.CAR);
	}
	
}

