import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IInchargeMasterType } from 'app/shared/model/incharge-master-type.model';
import { AccountService } from 'app/core';
import { InchargeMasterTypeService } from './incharge-master-type.service';

@Component({
    selector: 'jhi-incharge-master-type',
    templateUrl: './incharge-master-type.component.html'
})
export class InchargeMasterTypeComponent implements OnInit, OnDestroy {
    inchargeMasterTypes: IInchargeMasterType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected inchargeMasterTypeService: InchargeMasterTypeService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.inchargeMasterTypeService.query().subscribe(
            (res: HttpResponse<IInchargeMasterType[]>) => {
                this.inchargeMasterTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInInchargeMasterTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IInchargeMasterType) {
        return item.id;
    }

    registerChangeInInchargeMasterTypes() {
        this.eventSubscriber = this.eventManager.subscribe('inchargeMasterTypeListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
