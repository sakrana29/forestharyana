import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IDivisionMaster } from 'app/shared/model/division-master.model';
import { DivisionMasterService } from './division-master.service';

@Component({
    selector: 'jhi-division-master-update',
    templateUrl: './division-master-update.component.html'
})
export class DivisionMasterUpdateComponent implements OnInit {
    divisionMaster: IDivisionMaster;
    isSaving: boolean;

    constructor(protected divisionMasterService: DivisionMasterService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ divisionMaster }) => {
            this.divisionMaster = divisionMaster;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.divisionMaster.id !== undefined) {
            this.subscribeToSaveResponse(this.divisionMasterService.update(this.divisionMaster));
        } else {
            this.subscribeToSaveResponse(this.divisionMasterService.create(this.divisionMaster));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDivisionMaster>>) {
        result.subscribe((res: HttpResponse<IDivisionMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
