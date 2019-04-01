import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRangeMaster } from 'app/shared/model/range-master.model';
import { AccountService } from 'app/core';
import { RangeMasterService } from './range-master.service';

@Component({
    selector: 'jhi-range-master',
    templateUrl: './range-master.component.html'
})
export class RangeMasterComponent implements OnInit, OnDestroy {
    rangeMasters: IRangeMaster[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected rangeMasterService: RangeMasterService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.rangeMasterService.query().subscribe(
            (res: HttpResponse<IRangeMaster[]>) => {
                this.rangeMasters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRangeMasters();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRangeMaster) {
        return item.id;
    }

    registerChangeInRangeMasters() {
        this.eventSubscriber = this.eventManager.subscribe('rangeMasterListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
