import { Personne } from "./personne";
import { Image } from "./image";
import { Job } from "./job";
import { Review } from "./review";

export class Worker extends Personne {
    constructor(
        name: String,
        email: string,
        phoneNumber: string,
        location: string,
        public expertise: string,
        public daily_price: number,
        password: string,
        public serviceId: number,
        public reviews: Review[],
        id?: number,
        public image?: Image,

    ) { super(name, email, phoneNumber, location, password, id); }
}
