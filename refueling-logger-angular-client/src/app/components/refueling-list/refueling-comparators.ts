import { Refueling } from '../../models/refueling';


export function getComparatorByFuelType(sortStrategy: number) {
    return (ref1: Refueling, ref2: Refueling) => {
        if (ref1.fuelType.toString() > ref2.fuelType.toString()) {
            return sortStrategy;
        } else if (ref1.fuelType.toString() < ref2.fuelType.toString()) {
            return sortStrategy * (-1);
        } else {
            return 0;
        }
    };
}

export function getComparatorByFuelAmount(sortStrategy: number) {
    return (ref1: Refueling, ref2: Refueling) => {
        if (ref1.fuelAmount > ref2.fuelAmount) {
            return sortStrategy;
        } else if (ref1.fuelAmount < ref2.fuelAmount) {
            return sortStrategy * (-1);
        } else {
            return 0;
        }
    };
}

export function getComparatorByPerson(sortStrategy: number) {
    return (ref1: Refueling, ref2: Refueling) => {
        const ref1PersonName = ref1.person.firstName + ref1.person.lastName;
        const ref2PersonName = ref2.person.firstName + ref1.person.lastName;

        if (ref1PersonName > ref2PersonName) {
            return sortStrategy;
        } else if (ref1PersonName < ref2PersonName) {
            return sortStrategy * (-1);
        } else {
            return 0;
        }
    };
}

export function getComparatorByPayment(sortStrategy: number) {
    return (ref1: Refueling, ref2: Refueling) => {
        const ref1Payment = ref1.fuelAmount * ref1.priceForLiterInThisTime;
        const ref2Payment = ref2.fuelAmount * ref2.priceForLiterInThisTime;

        if (ref1Payment > ref2Payment) {
            return sortStrategy;
        } else if (ref1Payment < ref2Payment) {
            return sortStrategy * (-1);
        } else {
            return 0;
        }
    };
}

export function getComparatorByDateTime(sortStrategy: number) {
    return (ref1: Refueling, ref2: Refueling) => {
        const ref1Date = ref1.date;
        const ref2Date = ref2.date;
        const ref1Time = ref1.time;
        const ref2Time = ref2.time;

        if (ref1Date > ref2Date) {
            return sortStrategy;
        } else if (ref1Date < ref2Date) {
            return sortStrategy * (-1);
        } else {
            if (ref1Time > ref2Time) {
                return sortStrategy;
            } else if (ref1Time < ref2Time) {
                return sortStrategy * (-1);
            } else {
                return 0;
            }
        }
    };
}
