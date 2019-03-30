import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBeatMaster } from 'app/shared/model/beat-master.model';
import { BeatMasterService } from './beat-master.service';

@Component({
    selector: 'jhi-beat-master-delete-dialog',
    templateUrl: './beat-master-delete-dialog.component.html'
})
export class BeatMasterDeleteDialogComponent {
    beatMaster: IBeatMaster;

    constructor(
        protected beatMasterService: BeatMasterService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.beatMasterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'beatMasterListModification',
                content: 'Deleted an beatMaster'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-beat-master-delete-popup',
    template: ''
})
export class BeatMasterDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ beatMaster }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BeatMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.beatMaster = beatMaster;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
