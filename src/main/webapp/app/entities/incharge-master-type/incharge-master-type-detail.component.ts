import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInchargeMasterType } from 'app/shared/model/incharge-master-type.model';

@Component({
    selector: 'jhi-incharge-master-type-detail',
    templateUrl: './incharge-master-type-detail.component.html'
})
export class InchargeMasterTypeDetailComponent implements OnInit {
    inchargeMasterType: IInchargeMasterType;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ inchargeMasterType }) => {
            this.inchargeMasterType = inchargeMasterType;
        });
    }

    previousState() {
        window.history.back();
    }
}
