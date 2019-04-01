import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IRangeMaster } from 'app/shared/model/range-master.model';
import { RangeMasterService } from './range-master.service';

@Component({
    selector: 'jhi-range-master-update',
    templateUrl: './range-master-update.component.html'
})
export class RangeMasterUpdateComponent implements OnInit {
    rangeMaster: IRangeMaster;
    isSaving: boolean;

    constructor(protected rangeMasterService: RangeMasterService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rangeMaster }) => {
            this.rangeMaster = rangeMaster;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.rangeMaster.id !== undefined) {
            this.subscribeToSaveResponse(this.rangeMasterService.update(this.rangeMaster));
        } else {
            this.subscribeToSaveResponse(this.rangeMasterService.create(this.rangeMaster));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRangeMaster>>) {
        result.subscribe((res: HttpResponse<IRangeMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
