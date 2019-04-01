import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ForestharyanaSharedModule } from 'app/shared';
import {
    NurseryMasterComponent,
    NurseryMasterDetailComponent,
    NurseryMasterUpdateComponent,
    NurseryMasterDeletePopupComponent,
    NurseryMasterDeleteDialogComponent,
    nurseryMasterRoute,
    nurseryMasterPopupRoute
} from './';

const ENTITY_STATES = [...nurseryMasterRoute, ...nurseryMasterPopupRoute];

@NgModule({
    imports: [ForestharyanaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NurseryMasterComponent,
        NurseryMasterDetailComponent,
        NurseryMasterUpdateComponent,
        NurseryMasterDeleteDialogComponent,
        NurseryMasterDeletePopupComponent
    ],
    entryComponents: [
        NurseryMasterComponent,
        NurseryMasterUpdateComponent,
        NurseryMasterDeleteDialogComponent,
        NurseryMasterDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ForestharyanaNurseryMasterModule {}
