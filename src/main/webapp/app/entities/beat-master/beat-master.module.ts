import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ForestharyanaSharedModule } from 'app/shared';
import {
    BeatMasterComponent,
    BeatMasterDetailComponent,
    BeatMasterUpdateComponent,
    BeatMasterDeletePopupComponent,
    BeatMasterDeleteDialogComponent,
    beatMasterRoute,
    beatMasterPopupRoute
} from './';

const ENTITY_STATES = [...beatMasterRoute, ...beatMasterPopupRoute];

@NgModule({
    imports: [ForestharyanaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BeatMasterComponent,
        BeatMasterDetailComponent,
        BeatMasterUpdateComponent,
        BeatMasterDeleteDialogComponent,
        BeatMasterDeletePopupComponent
    ],
    entryComponents: [BeatMasterComponent, BeatMasterUpdateComponent, BeatMasterDeleteDialogComponent, BeatMasterDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ForestharyanaBeatMasterModule {}
