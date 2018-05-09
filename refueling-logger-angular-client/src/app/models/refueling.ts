import { Time } from '@angular/common';
import { Vehicle } from './vehicle';
import { Person } from './person';

export class Refueling {
    id: number;

    constructor(
        public vehicle?: Vehicle,
        public person?: Person,
        public meterStatusInThisTime?: number,
        public fuelAmount?: number,
        public priceForLiterInThisTime?: number,
        public date?: Date,
        public time?: Time,
        public fuelType?: FuelType,
    ) { }
}

export enum FuelType {
    PETROL, OIL, LPG
}
