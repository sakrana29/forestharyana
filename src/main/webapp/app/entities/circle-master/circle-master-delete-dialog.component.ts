import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICircleMaster } from 'app/shared/model/circle-master.model';
import { CircleMasterService } from './circle-master.service';

@Component({
    selector: 'jhi-circle-master-delete-dialog',
    templateUrl: './circle-master-delete-dialog.component.html'
})
export class CircleMasterDeleteDialogComponent {
    circleMaster: ICircleMaster;

    constructor(
        protected circleMasterService: CircleMasterService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.circleMasterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'circleMasterListModification',
                content: 'Deleted an circleMaster'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-circle-master-delete-popup',
    template: ''
})
export class CircleMasterDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ circleMaster }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CircleMasterDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.circleMaster = circleMaster;
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
