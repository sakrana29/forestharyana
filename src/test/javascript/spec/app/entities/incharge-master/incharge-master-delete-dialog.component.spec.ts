/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ForestharyanaTestModule } from '../../../test.module';
import { InchargeMasterDeleteDialogComponent } from 'app/entities/incharge-master/incharge-master-delete-dialog.component';
import { InchargeMasterService } from 'app/entities/incharge-master/incharge-master.service';

describe('Component Tests', () => {
    describe('InchargeMaster Management Delete Component', () => {
        let comp: InchargeMasterDeleteDialogComponent;
        let fixture: ComponentFixture<InchargeMasterDeleteDialogComponent>;
        let service: InchargeMasterService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [InchargeMasterDeleteDialogComponent]
            })
                .overrideTemplate(InchargeMasterDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InchargeMasterDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InchargeMasterService);
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
