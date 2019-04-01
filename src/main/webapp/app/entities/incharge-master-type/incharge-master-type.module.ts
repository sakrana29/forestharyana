import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ForestharyanaSharedModule } from 'app/shared';
import {
    InchargeMasterTypeComponent,
    InchargeMasterTypeDetailComponent,
    InchargeMasterTypeUpdateComponent,
    InchargeMasterTypeDeletePopupComponent,
    InchargeMasterTypeDeleteDialogComponent,
    inchargeMasterTypeRoute,
    inchargeMasterTypePopupRoute
} from './';

const ENTITY_STATES = [...inchargeMasterTypeRoute, ...inchargeMasterTypePopupRoute];

@NgModule({
    imports: [ForestharyanaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InchargeMasterTypeComponent,
        InchargeMasterTypeDetailComponent,
        InchargeMasterTypeUpdateComponent,
        InchargeMasterTypeDeleteDialogComponent,
        InchargeMasterTypeDeletePopupComponent
    ],
    entryComponents: [
        InchargeMasterTypeComponent,
        InchargeMasterTypeUpdateComponent,
        InchargeMasterTypeDeleteDialogComponent,
        InchargeMasterTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ForestharyanaInchargeMasterTypeModule {}
