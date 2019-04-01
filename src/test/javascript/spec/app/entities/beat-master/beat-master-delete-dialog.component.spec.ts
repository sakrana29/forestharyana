/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ForestharyanaTestModule } from '../../../test.module';
import { BeatMasterDeleteDialogComponent } from 'app/entities/beat-master/beat-master-delete-dialog.component';
import { BeatMasterService } from 'app/entities/beat-master/beat-master.service';

describe('Component Tests', () => {
    describe('BeatMaster Management Delete Component', () => {
        let comp: BeatMasterDeleteDialogComponent;
        let fixture: ComponentFixture<BeatMasterDeleteDialogComponent>;
        let service: BeatMasterService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [BeatMasterDeleteDialogComponent]
            })
                .overrideTemplate(BeatMasterDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BeatMasterDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BeatMasterService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete('9fec3727-3421-4967-b213-ba36557ca194');
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith('9fec3727-3421-4967-b213-ba36557ca194');
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
