import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStateMaster } from 'app/shared/model/state-master.model';

@Component({
    selector: 'jhi-state-master-detail',
    templateUrl: './state-master-detail.component.html'
})
export class StateMasterDetailComponent implements OnInit {
    stateMaster: IStateMaster;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ stateMaster }) => {
            this.stateMaster = stateMaster;
        });
    }

    previousState() {
        window.history.back();
    }
}
