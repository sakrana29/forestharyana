export interface ICircleMaster {
    id?: string;
    circleName?: string;
    stateName?: string;
    stateId?: string;
    isActive?: boolean;
}

export class CircleMaster implements ICircleMaster {
    constructor(
        public id?: string,
        public circleName?: string,
        public stateName?: string,
        public stateId?: string,
        public isActive?: boolean
    ) {
        this.isActive = this.isActive || false;
    }
}
