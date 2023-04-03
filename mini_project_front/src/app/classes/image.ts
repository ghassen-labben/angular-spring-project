export class Image {
    id: number;
    name: string;
    type: string;
    picByte: any;

    constructor(id: number, name: string, type: string, picByte: any) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.id = id;
    }
}
