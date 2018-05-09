export class Vehicle {
    id: number;

    constructor(
        public mark?: string,
        public model?: string,
        public yearOfProduction?: number,
        public meterStatus?: number,
        public vehicleType?: VehicleType
    ) { }
}

export enum VehicleType {
    CAR, MOTORBIKE
}
