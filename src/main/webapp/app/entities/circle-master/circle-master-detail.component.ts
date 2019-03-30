import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICircleMaster } from 'app/shared/model/circle-master.model';

@Component({
    selector: 'jhi-circle-master-detail',
    templateUrl: './circle-master-detail.component.html'
})
export class CircleMasterDetailComponent implements OnInit {
    circleMaster: ICircleMaster;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ circleMaster }) => {
            this.circleMaster = circleMaster;
        });
    }

    previousState() {
        window.history.back();
    }
}
