import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IBeatMaster } from 'app/shared/model/beat-master.model';
import { BeatMasterService } from './beat-master.service';

@Component({
    selector: 'jhi-beat-master-update',
    templateUrl: './beat-master-update.component.html'
})
export class BeatMasterUpdateComponent implements OnInit {
    beatMaster: IBeatMaster;
    isSaving: boolean;

    constructor(protected beatMasterService: BeatMasterService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ beatMaster }) => {
            this.beatMaster = beatMaster;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.beatMaster.id !== undefined) {
            this.subscribeToSaveResponse(this.beatMasterService.update(this.beatMaster));
        } else {
            this.subscribeToSaveResponse(this.beatMasterService.create(this.beatMaster));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IBeatMaster>>) {
        result.subscribe((res: HttpResponse<IBeatMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
