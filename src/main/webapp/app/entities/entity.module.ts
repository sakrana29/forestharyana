import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ForestharyanaStateMasterModule } from './state-master/state-master.module';
import { ForestharyanaCircleMasterModule } from './circle-master/circle-master.module';
import { ForestharyanaDivisionMasterModule } from './division-master/division-master.module';
import { ForestharyanaRangeMasterModule } from './range-master/range-master.module';
import { ForestharyanaBlockMasterModule } from './block-master/block-master.module';
import { ForestharyanaBeatMasterModule } from './beat-master/beat-master.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ForestharyanaStateMasterModule,
        ForestharyanaCircleMasterModule,
        ForestharyanaDivisionMasterModule,
        ForestharyanaRangeMasterModule,
        ForestharyanaBlockMasterModule,
        ForestharyanaBeatMasterModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ForestharyanaEntityModule {}
