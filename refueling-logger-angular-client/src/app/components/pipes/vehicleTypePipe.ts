import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'vehicleType' })
export class VehicleTypePipe implements PipeTransform {
    transform(value: string): string {
        if (value === 'CAR') {
            return 'Samochod';
        } else if (value === 'MOTORBIKE') {
            return 'Motor';
        }
    }
}
