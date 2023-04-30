export class User {
    constructor(
        public id: string, // optional property
        public fullName?: string,
        public email?: string,
        public phoneNumber?: string,
        public userType?: string
    ){

    }
}
