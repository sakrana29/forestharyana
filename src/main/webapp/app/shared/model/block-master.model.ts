export interface IBlockMaster {
    id?: string;
    blockName?: string;
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

export class BlockMaster implements IBlockMaster {
    constructor(
        public id?: string,
        public blockName?: string,
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
