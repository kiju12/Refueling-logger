export class Person {
    id: number;

    constructor(
        public firstName?: string,
        public lastName?: string
    ) { }
}

export interface PersonForm {
    firstName: string;
    lastName: string;
}
