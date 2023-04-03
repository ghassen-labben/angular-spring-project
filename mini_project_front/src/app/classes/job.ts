import { Image } from './image';
import { Worker } from './worker';

export class Job {
    id: number;
    name: string;
    description: string;
    price: number;
    workers!: Worker[];
    image!: Image;
    constructor(id: number, name: string, description: string, price: number, image: Image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }
}
