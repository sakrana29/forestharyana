import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDivisionMaster } from 'app/shared/model/division-master.model';

@Component({
    selector: 'jhi-division-master-detail',
    templateUrl: './division-master-detail.component.html'
})
export class DivisionMasterDetailComponent implements OnInit {
    divisionMaster: IDivisionMaster;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ divisionMaster }) => {
            this.divisionMaster = divisionMaster;
        });
    }

    previousState() {
        window.history.back();
    }
}
