import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDivisionMaster } from 'app/shared/model/division-master.model';
import { DivisionMasterService } from './division-master.service';

@Component({
    selector: 'jhi-division-master-delete-dialog',
    templateUrl: './division-master-delete-dialog.component.html'
})
export class DivisionMasterDeleteDialogComponent {
    divisionMaster: IDivisionMaster;

    constructor(
        protected divisionMasterService: DivisionMasterService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.divisionMasterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'divisionMasterListModification',
                content: 'Deleted an divisionMaster'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-division-master-delete-popup',
    template: ''
})
export class DivisionMasterDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ divisionMaster }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DivisionMasterDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.divisionMaster = divisionMaster;
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
