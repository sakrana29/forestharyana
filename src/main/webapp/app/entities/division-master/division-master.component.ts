import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDivisionMaster } from 'app/shared/model/division-master.model';
import { AccountService } from 'app/core';
import { DivisionMasterService } from './division-master.service';

@Component({
    selector: 'jhi-division-master',
    templateUrl: './division-master.component.html'
})
export class DivisionMasterComponent implements OnInit, OnDestroy {
    divisionMasters: IDivisionMaster[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected divisionMasterService: DivisionMasterService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.divisionMasterService.query().subscribe(
            (res: HttpResponse<IDivisionMaster[]>) => {
                this.divisionMasters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDivisionMasters();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDivisionMaster) {
        return item.id;
    }

    registerChangeInDivisionMasters() {
        this.eventSubscriber = this.eventManager.subscribe('divisionMasterListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
