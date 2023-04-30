export class EquipmentDto {
    constructor(
        public model?: string,
        public serialNumber?: string,
        public equipmentName?: string,
        public calibrationDate?: Date,
        public deviceType?: string,
        public userId?: string
    ){}
}
