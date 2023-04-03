import { Worker } from './worker';
import { Customer } from './customer';

export class Review {
    worker!: Worker;
    customer!: Customer;
    rating: number;
    comment: string;

    constructor(rating: number, comment: string) {
        this.rating = rating;
        this.comment = comment;
    }
}
