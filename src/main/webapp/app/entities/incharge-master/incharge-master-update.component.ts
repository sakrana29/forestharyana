import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IInchargeMaster } from 'app/shared/model/incharge-master.model';
import { InchargeMasterService } from './incharge-master.service';

@Component({
    selector: 'jhi-incharge-master-update',
    templateUrl: './incharge-master-update.component.html'
})
export class InchargeMasterUpdateComponent implements OnInit {
    inchargeMaster: IInchargeMaster;
    isSaving: boolean;
    chargeTakenFrom: string;
    chargeReleaveDate: string;

    constructor(protected inchargeMasterService: InchargeMasterService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ inchargeMaster }) => {
            this.inchargeMaster = inchargeMaster;
            this.chargeTakenFrom =
                this.inchargeMaster.chargeTakenFrom != null ? this.inchargeMaster.chargeTakenFrom.format(DATE_TIME_FORMAT) : null;
            this.chargeReleaveDate =
                this.inchargeMaster.chargeReleaveDate != null ? this.inchargeMaster.chargeReleaveDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.inchargeMaster.chargeTakenFrom = this.chargeTakenFrom != null ? moment(this.chargeTakenFrom, DATE_TIME_FORMAT) : null;
        this.inchargeMaster.chargeReleaveDate = this.chargeReleaveDate != null ? moment(this.chargeReleaveDate, DATE_TIME_FORMAT) : null;
        if (this.inchargeMaster.id !== undefined) {
            this.subscribeToSaveResponse(this.inchargeMasterService.update(this.inchargeMaster));
        } else {
            this.subscribeToSaveResponse(this.inchargeMasterService.create(this.inchargeMaster));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IInchargeMaster>>) {
        result.subscribe((res: HttpResponse<IInchargeMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
