import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INurseryMaster } from 'app/shared/model/nursery-master.model';
import { NurseryMasterService } from './nursery-master.service';

@Component({
    selector: 'jhi-nursery-master-delete-dialog',
    templateUrl: './nursery-master-delete-dialog.component.html'
})
export class NurseryMasterDeleteDialogComponent {
    nurseryMaster: INurseryMaster;

    constructor(
        protected nurseryMasterService: NurseryMasterService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.nurseryMasterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'nurseryMasterListModification',
                content: 'Deleted an nurseryMaster'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-nursery-master-delete-popup',
    template: ''
})
export class NurseryMasterDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nurseryMaster }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NurseryMasterDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.nurseryMaster = nurseryMaster;
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
