/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ForestharyanaTestModule } from '../../../test.module';
import { StateMasterDeleteDialogComponent } from 'app/entities/state-master/state-master-delete-dialog.component';
import { StateMasterService } from 'app/entities/state-master/state-master.service';

describe('Component Tests', () => {
    describe('StateMaster Management Delete Component', () => {
        let comp: StateMasterDeleteDialogComponent;
        let fixture: ComponentFixture<StateMasterDeleteDialogComponent>;
        let service: StateMasterService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [StateMasterDeleteDialogComponent]
            })
                .overrideTemplate(StateMasterDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StateMasterDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StateMasterService);
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
