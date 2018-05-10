export class Vehicle {
    id: number;

    constructor(
        public mark?: string,
        public model?: string,
        public yearOfProduction?: number,
        public meterStatus?: number,
        public vehicleType?: string
    ) { }
}

export interface VehicleForm {
    mark: string;
    model: string;
    yearOfProduction: number;
    meterStatus: number;
    vehicleType: string;
}

export enum VehicleType {
    CAR, MOTORBIKE
}


