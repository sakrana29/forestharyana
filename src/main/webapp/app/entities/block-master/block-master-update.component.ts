import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IBlockMaster } from 'app/shared/model/block-master.model';
import { BlockMasterService } from './block-master.service';

@Component({
    selector: 'jhi-block-master-update',
    templateUrl: './block-master-update.component.html'
})
export class BlockMasterUpdateComponent implements OnInit {
    blockMaster: IBlockMaster;
    isSaving: boolean;

    constructor(protected blockMasterService: BlockMasterService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ blockMaster }) => {
            this.blockMaster = blockMaster;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.blockMaster.id !== undefined) {
            this.subscribeToSaveResponse(this.blockMasterService.update(this.blockMaster));
        } else {
            this.subscribeToSaveResponse(this.blockMasterService.create(this.blockMaster));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IBlockMaster>>) {
        result.subscribe((res: HttpResponse<IBlockMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
