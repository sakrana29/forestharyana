import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInchargeMaster } from 'app/shared/model/incharge-master.model';
import { InchargeMasterService } from './incharge-master.service';

@Component({
    selector: 'jhi-incharge-master-delete-dialog',
    templateUrl: './incharge-master-delete-dialog.component.html'
})
export class InchargeMasterDeleteDialogComponent {
    inchargeMaster: IInchargeMaster;

    constructor(
        protected inchargeMasterService: InchargeMasterService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.inchargeMasterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'inchargeMasterListModification',
                content: 'Deleted an inchargeMaster'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-incharge-master-delete-popup',
    template: ''
})
export class InchargeMasterDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ inchargeMaster }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InchargeMasterDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.inchargeMaster = inchargeMaster;
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
