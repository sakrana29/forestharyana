export interface IBeatMaster {
    id?: string;
    beatName?: string;
    blockName?: string;
    blockId?: string;
    rangeName?: string;
    rangeId?: string;
    divisionName?: string;
    divisionId?: string;
    circleName?: string;
    circleId?: string;
    stateName?: string;
    stateId?: string;
    isActive?: boolean;
}

export class BeatMaster implements IBeatMaster {
    constructor(
        public id?: string,
        public beatName?: string,
        public blockName?: string,
        public blockId?: string,
        public rangeName?: string,
        public rangeId?: string,
        public divisionName?: string,
        public divisionId?: string,
        public circleName?: string,
        public circleId?: string,
        public stateName?: string,
        public stateId?: string,
        public isActive?: boolean
    ) {
        this.isActive = this.isActive || false;
    }
}
