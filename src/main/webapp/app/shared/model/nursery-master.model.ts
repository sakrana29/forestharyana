import { Moment } from 'moment';

export interface INurseryMaster {
    id?: string;
    circleName?: string;
    circleId?: string;
    divisionName?: string;
    divisionId?: string;
    rangeName?: string;
    rangeId?: string;
    blockname?: string;
    blockId?: string;
    beatName?: string;
    beatId?: string;
    nurseryName?: string;
    nurseryAddress?: string;
    nurseryEstablishment?: Moment;
    nurseryArea?: number;
    sourceOfIrrigation?: string;
    soilType?: string;
    otherDetail?: string;
}

export class NurseryMaster implements INurseryMaster {
    constructor(
        public id?: string,
        public circleName?: string,
        public circleId?: string,
        public divisionName?: string,
        public divisionId?: string,
        public rangeName?: string,
        public rangeId?: string,
        public blockname?: string,
        public blockId?: string,
        public beatName?: string,
        public beatId?: string,
        public nurseryName?: string,
        public nurseryAddress?: string,
        public nurseryEstablishment?: Moment,
        public nurseryArea?: number,
        public sourceOfIrrigation?: string,
        public soilType?: string,
        public otherDetail?: string
    ) {}
}
