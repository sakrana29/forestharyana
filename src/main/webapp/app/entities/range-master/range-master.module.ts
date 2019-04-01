import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ForestharyanaSharedModule } from 'app/shared';
import {
    RangeMasterComponent,
    RangeMasterDetailComponent,
    RangeMasterUpdateComponent,
    RangeMasterDeletePopupComponent,
    RangeMasterDeleteDialogComponent,
    rangeMasterRoute,
    rangeMasterPopupRoute
} from './';

const ENTITY_STATES = [...rangeMasterRoute, ...rangeMasterPopupRoute];

@NgModule({
    imports: [ForestharyanaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RangeMasterComponent,
        RangeMasterDetailComponent,
        RangeMasterUpdateComponent,
        RangeMasterDeleteDialogComponent,
        RangeMasterDeletePopupComponent
    ],
    entryComponents: [RangeMasterComponent, RangeMasterUpdateComponent, RangeMasterDeleteDialogComponent, RangeMasterDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ForestharyanaRangeMasterModule {}
