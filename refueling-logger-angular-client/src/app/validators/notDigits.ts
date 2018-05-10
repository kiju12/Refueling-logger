import { FormControl } from '@angular/forms';

export function notDigitsValidator(control: FormControl): any {
    const inputValue: string = control.value;
    if (inputValue === null || inputValue === '') {
        return null;
    }

    if (hasDigits(inputValue)) {
        return {
            hasDigits: {
                inputValue: inputValue
            }
        };
    }

    return null;
}

function hasDigits(str: string): boolean {
    return /\d/.test(str);
}