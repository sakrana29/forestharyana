import { Moment } from 'moment';

export interface IInchargeMaster {
    id?: string;
    inchargeMasterType?: string;
    inchargeMasterTypeId?: string;
    nurseryName?: string;
    nurseryId?: string;
    inchargeName?: string;
    inchargeDesignation?: string;
    inchargeMobile?: string;
    chargeTakenFrom?: Moment;
    chargeReleaveDate?: Moment;
    isActive?: boolean;
}

export class InchargeMaster implements IInchargeMaster {
    constructor(
        public id?: string,
        public inchargeMasterType?: string,
        public inchargeMasterTypeId?: string,
        public nurseryName?: string,
        public nurseryId?: string,
        public inchargeName?: string,
        public inchargeDesignation?: string,
        public inchargeMobile?: string,
        public chargeTakenFrom?: Moment,
        public chargeReleaveDate?: Moment,
        public isActive?: boolean
    ) {
        this.isActive = this.isActive || false;
    }
}
