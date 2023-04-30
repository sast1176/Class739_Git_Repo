export class Equipment {
    constructor(
        public id : string,
        public model: string,
        public serialNumber: string,
        public equipmentName: string,
        public calibrationDate: Date,
        public deviceType: string,
        public userId : string
    ){}
}

