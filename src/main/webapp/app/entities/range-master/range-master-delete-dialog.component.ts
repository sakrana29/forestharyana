import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRangeMaster } from 'app/shared/model/range-master.model';
import { RangeMasterService } from './range-master.service';

@Component({
    selector: 'jhi-range-master-delete-dialog',
    templateUrl: './range-master-delete-dialog.component.html'
})
export class RangeMasterDeleteDialogComponent {
    rangeMaster: IRangeMaster;

    constructor(
        protected rangeMasterService: RangeMasterService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.rangeMasterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'rangeMasterListModification',
                content: 'Deleted an rangeMaster'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-range-master-delete-popup',
    template: ''
})
export class RangeMasterDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rangeMaster }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RangeMasterDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.rangeMaster = rangeMaster;
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
