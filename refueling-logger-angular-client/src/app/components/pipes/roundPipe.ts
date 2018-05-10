import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'roundThis' })
export class RoundPipe implements PipeTransform {
    transform(value: string): string {
        let num: number = +value;
        num = Math.round(num * 100) / 100;

        return num.toString();
    }
}
