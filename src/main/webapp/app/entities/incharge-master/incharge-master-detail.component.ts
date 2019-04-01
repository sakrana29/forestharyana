import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInchargeMaster } from 'app/shared/model/incharge-master.model';

@Component({
    selector: 'jhi-incharge-master-detail',
    templateUrl: './incharge-master-detail.component.html'
})
export class InchargeMasterDetailComponent implements OnInit {
    inchargeMaster: IInchargeMaster;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ inchargeMaster }) => {
            this.inchargeMaster = inchargeMaster;
        });
    }

    previousState() {
        window.history.back();
    }
}
