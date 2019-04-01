import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ForestharyanaSharedModule } from 'app/shared';
import {
    CircleMasterComponent,
    CircleMasterDetailComponent,
    CircleMasterUpdateComponent,
    CircleMasterDeletePopupComponent,
    CircleMasterDeleteDialogComponent,
    circleMasterRoute,
    circleMasterPopupRoute
} from './';

const ENTITY_STATES = [...circleMasterRoute, ...circleMasterPopupRoute];

@NgModule({
    imports: [ForestharyanaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CircleMasterComponent,
        CircleMasterDetailComponent,
        CircleMasterUpdateComponent,
        CircleMasterDeleteDialogComponent,
        CircleMasterDeletePopupComponent
    ],
    entryComponents: [
        CircleMasterComponent,
        CircleMasterUpdateComponent,
        CircleMasterDeleteDialogComponent,
        CircleMasterDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ForestharyanaCircleMasterModule {}
