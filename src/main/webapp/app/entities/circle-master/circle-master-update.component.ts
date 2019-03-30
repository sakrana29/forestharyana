import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICircleMaster } from 'app/shared/model/circle-master.model';
import { CircleMasterService } from './circle-master.service';

@Component({
    selector: 'jhi-circle-master-update',
    templateUrl: './circle-master-update.component.html'
})
export class CircleMasterUpdateComponent implements OnInit {
    circleMaster: ICircleMaster;
    isSaving: boolean;

    constructor(protected circleMasterService: CircleMasterService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ circleMaster }) => {
            this.circleMaster = circleMaster;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.circleMaster.id !== undefined) {
            this.subscribeToSaveResponse(this.circleMasterService.update(this.circleMaster));
        } else {
            this.subscribeToSaveResponse(this.circleMasterService.create(this.circleMaster));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICircleMaster>>) {
        result.subscribe((res: HttpResponse<ICircleMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
