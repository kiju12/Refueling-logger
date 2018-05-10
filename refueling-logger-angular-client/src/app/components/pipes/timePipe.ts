import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'time' })
export class TimePipe implements PipeTransform {
    transform(value: string): string {
        value = value.substring(0, 5);

        return value;
    }
}