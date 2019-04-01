export interface IInchargeMasterType {
    id?: string;
    inchargeMasterType?: string;
    isActive?: boolean;
}

export class InchargeMasterType implements IInchargeMasterType {
    constructor(public id?: string, public inchargeMasterType?: string, public isActive?: boolean) {
        this.isActive = this.isActive || false;
    }
}
