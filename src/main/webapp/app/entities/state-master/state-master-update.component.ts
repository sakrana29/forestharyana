import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IStateMaster } from 'app/shared/model/state-master.model';
import { StateMasterService } from './state-master.service';

@Component({
    selector: 'jhi-state-master-update',
    templateUrl: './state-master-update.component.html'
})
export class StateMasterUpdateComponent implements OnInit {
    stateMaster: IStateMaster;
    isSaving: boolean;

    constructor(protected stateMasterService: StateMasterService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ stateMaster }) => {
            this.stateMaster = stateMaster;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.stateMaster.id !== undefined) {
            this.subscribeToSaveResponse(this.stateMasterService.update(this.stateMaster));
        } else {
            this.subscribeToSaveResponse(this.stateMasterService.create(this.stateMaster));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IStateMaster>>) {
        result.subscribe((res: HttpResponse<IStateMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
