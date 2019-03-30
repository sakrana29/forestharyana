import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ForestharyanaSharedModule } from 'app/shared';
import {
    BlockMasterComponent,
    BlockMasterDetailComponent,
    BlockMasterUpdateComponent,
    BlockMasterDeletePopupComponent,
    BlockMasterDeleteDialogComponent,
    blockMasterRoute,
    blockMasterPopupRoute
} from './';

const ENTITY_STATES = [...blockMasterRoute, ...blockMasterPopupRoute];

@NgModule({
    imports: [ForestharyanaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BlockMasterComponent,
        BlockMasterDetailComponent,
        BlockMasterUpdateComponent,
        BlockMasterDeleteDialogComponent,
        BlockMasterDeletePopupComponent
    ],
    entryComponents: [BlockMasterComponent, BlockMasterUpdateComponent, BlockMasterDeleteDialogComponent, BlockMasterDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ForestharyanaBlockMasterModule {}
