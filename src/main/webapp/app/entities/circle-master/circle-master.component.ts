import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICircleMaster } from 'app/shared/model/circle-master.model';
import { AccountService } from 'app/core';
import { CircleMasterService } from './circle-master.service';

@Component({
    selector: 'jhi-circle-master',
    templateUrl: './circle-master.component.html'
})
export class CircleMasterComponent implements OnInit, OnDestroy {
    circleMasters: ICircleMaster[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected circleMasterService: CircleMasterService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.circleMasterService.query().subscribe(
            (res: HttpResponse<ICircleMaster[]>) => {
                this.circleMasters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCircleMasters();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICircleMaster) {
        return item.id;
    }

    registerChangeInCircleMasters() {
        this.eventSubscriber = this.eventManager.subscribe('circleMasterListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
