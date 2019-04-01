import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInchargeMasterType } from 'app/shared/model/incharge-master-type.model';
import { InchargeMasterTypeService } from './incharge-master-type.service';

@Component({
    selector: 'jhi-incharge-master-type-delete-dialog',
    templateUrl: './incharge-master-type-delete-dialog.component.html'
})
export class InchargeMasterTypeDeleteDialogComponent {
    inchargeMasterType: IInchargeMasterType;

    constructor(
        protected inchargeMasterTypeService: InchargeMasterTypeService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.inchargeMasterTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'inchargeMasterTypeListModification',
                content: 'Deleted an inchargeMasterType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-incharge-master-type-delete-popup',
    template: ''
})
export class InchargeMasterTypeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ inchargeMasterType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InchargeMasterTypeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.inchargeMasterType = inchargeMasterType;
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
