import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IStateMaster } from 'app/shared/model/state-master.model';
import { AccountService } from 'app/core';
import { StateMasterService } from './state-master.service';

@Component({
    selector: 'jhi-state-master',
    templateUrl: './state-master.component.html'
})
export class StateMasterComponent implements OnInit, OnDestroy {
    stateMasters: IStateMaster[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected stateMasterService: StateMasterService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.stateMasterService.query().subscribe(
            (res: HttpResponse<IStateMaster[]>) => {
                this.stateMasters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInStateMasters();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IStateMaster) {
        return item.id;
    }

    registerChangeInStateMasters() {
        this.eventSubscriber = this.eventManager.subscribe('stateMasterListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
