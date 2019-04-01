import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ForestharyanaSharedModule } from 'app/shared';
import {
    DivisionMasterComponent,
    DivisionMasterDetailComponent,
    DivisionMasterUpdateComponent,
    DivisionMasterDeletePopupComponent,
    DivisionMasterDeleteDialogComponent,
    divisionMasterRoute,
    divisionMasterPopupRoute
} from './';

const ENTITY_STATES = [...divisionMasterRoute, ...divisionMasterPopupRoute];

@NgModule({
    imports: [ForestharyanaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DivisionMasterComponent,
        DivisionMasterDetailComponent,
        DivisionMasterUpdateComponent,
        DivisionMasterDeleteDialogComponent,
        DivisionMasterDeletePopupComponent
    ],
    entryComponents: [
        DivisionMasterComponent,
        DivisionMasterUpdateComponent,
        DivisionMasterDeleteDialogComponent,
        DivisionMasterDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ForestharyanaDivisionMasterModule {}
