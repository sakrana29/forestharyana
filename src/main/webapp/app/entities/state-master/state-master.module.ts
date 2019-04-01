import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ForestharyanaSharedModule } from 'app/shared';
import {
    StateMasterComponent,
    StateMasterDetailComponent,
    StateMasterUpdateComponent,
    StateMasterDeletePopupComponent,
    StateMasterDeleteDialogComponent,
    stateMasterRoute,
    stateMasterPopupRoute
} from './';

const ENTITY_STATES = [...stateMasterRoute, ...stateMasterPopupRoute];

@NgModule({
    imports: [ForestharyanaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StateMasterComponent,
        StateMasterDetailComponent,
        StateMasterUpdateComponent,
        StateMasterDeleteDialogComponent,
        StateMasterDeletePopupComponent
    ],
    entryComponents: [StateMasterComponent, StateMasterUpdateComponent, StateMasterDeleteDialogComponent, StateMasterDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ForestharyanaStateMasterModule {}
