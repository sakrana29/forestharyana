import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { INurseryMaster } from 'app/shared/model/nursery-master.model';
import { AccountService } from 'app/core';
import { NurseryMasterService } from './nursery-master.service';

@Component({
    selector: 'jhi-nursery-master',
    templateUrl: './nursery-master.component.html'
})
export class NurseryMasterComponent implements OnInit, OnDestroy {
    nurseryMasters: INurseryMaster[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected nurseryMasterService: NurseryMasterService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.nurseryMasterService.query().subscribe(
            (res: HttpResponse<INurseryMaster[]>) => {
                this.nurseryMasters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInNurseryMasters();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: INurseryMaster) {
        return item.id;
    }

    registerChangeInNurseryMasters() {
        this.eventSubscriber = this.eventManager.subscribe('nurseryMasterListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
