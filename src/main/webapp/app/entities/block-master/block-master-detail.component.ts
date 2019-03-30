import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBlockMaster } from 'app/shared/model/block-master.model';

@Component({
    selector: 'jhi-block-master-detail',
    templateUrl: './block-master-detail.component.html'
})
export class BlockMasterDetailComponent implements OnInit {
    blockMaster: IBlockMaster;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ blockMaster }) => {
            this.blockMaster = blockMaster;
        });
    }

    previousState() {
        window.history.back();
    }
}
