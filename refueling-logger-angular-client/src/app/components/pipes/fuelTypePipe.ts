import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'fuelType' })
export class FuelTypePipe implements PipeTransform {
    transform(value: string): string {
        if (value === 'PETROL') {
            return 'Benzyna';
        } else if (value === 'OIL') {
            return 'Ropa';
        } else if (value === 'LPG') {
            return 'LPG';
        }
    }
}
