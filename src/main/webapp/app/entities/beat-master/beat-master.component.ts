import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBeatMaster } from 'app/shared/model/beat-master.model';
import { AccountService } from 'app/core';
import { BeatMasterService } from './beat-master.service';

@Component({
    selector: 'jhi-beat-master',
    templateUrl: './beat-master.component.html'
})
export class BeatMasterComponent implements OnInit, OnDestroy {
    beatMasters: IBeatMaster[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected beatMasterService: BeatMasterService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.beatMasterService.query().subscribe(
            (res: HttpResponse<IBeatMaster[]>) => {
                this.beatMasters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBeatMasters();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBeatMaster) {
        return item.id;
    }

    registerChangeInBeatMasters() {
        this.eventSubscriber = this.eventManager.subscribe('beatMasterListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
