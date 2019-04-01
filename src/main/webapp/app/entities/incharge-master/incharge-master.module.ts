import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ForestharyanaSharedModule } from 'app/shared';
import {
    InchargeMasterComponent,
    InchargeMasterDetailComponent,
    InchargeMasterUpdateComponent,
    InchargeMasterDeletePopupComponent,
    InchargeMasterDeleteDialogComponent,
    inchargeMasterRoute,
    inchargeMasterPopupRoute
} from './';

const ENTITY_STATES = [...inchargeMasterRoute, ...inchargeMasterPopupRoute];

@NgModule({
    imports: [ForestharyanaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InchargeMasterComponent,
        InchargeMasterDetailComponent,
        InchargeMasterUpdateComponent,
        InchargeMasterDeleteDialogComponent,
        InchargeMasterDeletePopupComponent
    ],
    entryComponents: [
        InchargeMasterComponent,
        InchargeMasterUpdateComponent,
        InchargeMasterDeleteDialogComponent,
        InchargeMasterDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ForestharyanaInchargeMasterModule {}
