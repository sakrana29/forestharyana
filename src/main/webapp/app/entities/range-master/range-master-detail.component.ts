import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRangeMaster } from 'app/shared/model/range-master.model';

@Component({
    selector: 'jhi-range-master-detail',
    templateUrl: './range-master-detail.component.html'
})
export class RangeMasterDetailComponent implements OnInit {
    rangeMaster: IRangeMaster;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rangeMaster }) => {
            this.rangeMaster = rangeMaster;
        });
    }

    previousState() {
        window.history.back();
    }
}
