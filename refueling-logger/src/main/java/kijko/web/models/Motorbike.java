package kijko.web.models;

import javax.persistence.Entity;

import kijko.web.models.enums.VehicleType;

@Entity
public class Motorbike extends Vehicle {

	public Motorbike() {
		super(VehicleType.MOTORBIKE);
	}
	
}

