export interface IRangeMaster {
    id?: string;
    rangeName?: string;
    divisionNmae?: string;
    divisionId?: string;
    circleName?: string;
    circleId?: string;
    stateName?: string;
    isActive?: boolean;
    stateId?: string;
}

export class RangeMaster implements IRangeMaster {
    constructor(
        public id?: string,
        public rangeName?: string,
        public divisionNmae?: string,
        public divisionId?: string,
        public circleName?: string,
        public circleId?: string,
        public stateName?: string,
        public isActive?: boolean,
        public stateId?: string
    ) {
        this.isActive = this.isActive || false;
    }
}
