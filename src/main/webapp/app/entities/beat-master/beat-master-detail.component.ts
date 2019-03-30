import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBeatMaster } from 'app/shared/model/beat-master.model';

@Component({
    selector: 'jhi-beat-master-detail',
    templateUrl: './beat-master-detail.component.html'
})
export class BeatMasterDetailComponent implements OnInit {
    beatMaster: IBeatMaster;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ beatMaster }) => {
            this.beatMaster = beatMaster;
        });
    }

    previousState() {
        window.history.back();
    }
}
