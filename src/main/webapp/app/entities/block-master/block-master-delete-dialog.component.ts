import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBlockMaster } from 'app/shared/model/block-master.model';
import { BlockMasterService } from './block-master.service';

@Component({
    selector: 'jhi-block-master-delete-dialog',
    templateUrl: './block-master-delete-dialog.component.html'
})
export class BlockMasterDeleteDialogComponent {
    blockMaster: IBlockMaster;

    constructor(
        protected blockMasterService: BlockMasterService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.blockMasterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'blockMasterListModification',
                content: 'Deleted an blockMaster'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-block-master-delete-popup',
    template: ''
})
export class BlockMasterDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ blockMaster }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BlockMasterDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.blockMaster = blockMaster;
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
