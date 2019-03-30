export interface IDivisionMaster {
    id?: string;
    divisionName?: string;
    circleName?: string;
    circleId?: string;
    stateName?: string;
    stateId?: string;
    isActive?: boolean;
}

export class DivisionMaster implements IDivisionMaster {
    constructor(
        public id?: string,
        public divisionName?: string,
        public circleName?: string,
        public circleId?: string,
        public stateName?: string,
        public stateId?: string,
        public isActive?: boolean
    ) {
        this.isActive = this.isActive || false;
    }
}
