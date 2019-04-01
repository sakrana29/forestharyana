import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IInchargeMasterType } from 'app/shared/model/incharge-master-type.model';
import { InchargeMasterTypeService } from './incharge-master-type.service';

@Component({
    selector: 'jhi-incharge-master-type-update',
    templateUrl: './incharge-master-type-update.component.html'
})
export class InchargeMasterTypeUpdateComponent implements OnInit {
    inchargeMasterType: IInchargeMasterType;
    isSaving: boolean;

    constructor(protected inchargeMasterTypeService: InchargeMasterTypeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ inchargeMasterType }) => {
            this.inchargeMasterType = inchargeMasterType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.inchargeMasterType.id !== undefined) {
            this.subscribeToSaveResponse(this.inchargeMasterTypeService.update(this.inchargeMasterType));
        } else {
            this.subscribeToSaveResponse(this.inchargeMasterTypeService.create(this.inchargeMasterType));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IInchargeMasterType>>) {
        result.subscribe((res: HttpResponse<IInchargeMasterType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
