import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IInchargeMaster } from 'app/shared/model/incharge-master.model';
import { AccountService } from 'app/core';
import { InchargeMasterService } from './incharge-master.service';

@Component({
    selector: 'jhi-incharge-master',
    templateUrl: './incharge-master.component.html'
})
export class InchargeMasterComponent implements OnInit, OnDestroy {
    inchargeMasters: IInchargeMaster[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected inchargeMasterService: InchargeMasterService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.inchargeMasterService.query().subscribe(
            (res: HttpResponse<IInchargeMaster[]>) => {
                this.inchargeMasters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInInchargeMasters();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IInchargeMaster) {
        return item.id;
    }

    registerChangeInInchargeMasters() {
        this.eventSubscriber = this.eventManager.subscribe('inchargeMasterListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
