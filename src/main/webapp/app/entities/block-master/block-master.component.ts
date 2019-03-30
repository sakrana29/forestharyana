import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBlockMaster } from 'app/shared/model/block-master.model';
import { AccountService } from 'app/core';
import { BlockMasterService } from './block-master.service';

@Component({
    selector: 'jhi-block-master',
    templateUrl: './block-master.component.html'
})
export class BlockMasterComponent implements OnInit, OnDestroy {
    blockMasters: IBlockMaster[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected blockMasterService: BlockMasterService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.blockMasterService.query().subscribe(
            (res: HttpResponse<IBlockMaster[]>) => {
                this.blockMasters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBlockMasters();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBlockMaster) {
        return item.id;
    }

    registerChangeInBlockMasters() {
        this.eventSubscriber = this.eventManager.subscribe('blockMasterListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
