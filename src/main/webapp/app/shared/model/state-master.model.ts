export interface IStateMaster {
    id?: string;
    stateName?: string;
    isActive?: boolean;
}

export class StateMaster implements IStateMaster {
    constructor(public id?: string, public stateName?: string, public isActive?: boolean) {
        this.isActive = this.isActive || false;
    }
}
