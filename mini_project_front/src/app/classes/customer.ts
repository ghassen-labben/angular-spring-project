import { Personne } from "./personne";
import { Image } from "./image";
export class Customer extends Personne {
    constructor(
        name: string,
        email: string,
        phoneNumber: string,
        location: string,
        password: string,
        id?: number,
        public image?: Image,
    ) { super(name, email, phoneNumber, location, password); }
}
