import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INurseryMaster } from 'app/shared/model/nursery-master.model';

@Component({
    selector: 'jhi-nursery-master-detail',
    templateUrl: './nursery-master-detail.component.html'
})
export class NurseryMasterDetailComponent implements OnInit {
    nurseryMaster: INurseryMaster;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nurseryMaster }) => {
            this.nurseryMaster = nurseryMaster;
        });
    }

    previousState() {
        window.history.back();
    }
}
