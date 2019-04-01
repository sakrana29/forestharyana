import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { INurseryMaster } from 'app/shared/model/nursery-master.model';
import { NurseryMasterService } from './nursery-master.service';

@Component({
    selector: 'jhi-nursery-master-update',
    templateUrl: './nursery-master-update.component.html'
})
export class NurseryMasterUpdateComponent implements OnInit {
    nurseryMaster: INurseryMaster;
    isSaving: boolean;
    nurseryEstablishment: string;

    constructor(protected nurseryMasterService: NurseryMasterService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nurseryMaster }) => {
            this.nurseryMaster = nurseryMaster;
            this.nurseryEstablishment =
                this.nurseryMaster.nurseryEstablishment != null ? this.nurseryMaster.nurseryEstablishment.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.nurseryMaster.nurseryEstablishment =
            this.nurseryEstablishment != null ? moment(this.nurseryEstablishment, DATE_TIME_FORMAT) : null;
        if (this.nurseryMaster.id !== undefined) {
            this.subscribeToSaveResponse(this.nurseryMasterService.update(this.nurseryMaster));
        } else {
            this.subscribeToSaveResponse(this.nurseryMasterService.create(this.nurseryMaster));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INurseryMaster>>) {
        result.subscribe((res: HttpResponse<INurseryMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
