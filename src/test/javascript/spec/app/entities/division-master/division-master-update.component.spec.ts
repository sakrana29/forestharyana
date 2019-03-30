/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { DivisionMasterUpdateComponent } from 'app/entities/division-master/division-master-update.component';
import { DivisionMasterService } from 'app/entities/division-master/division-master.service';
import { DivisionMaster } from 'app/shared/model/division-master.model';

describe('Component Tests', () => {
    describe('DivisionMaster Management Update Component', () => {
        let comp: DivisionMasterUpdateComponent;
        let fixture: ComponentFixture<DivisionMasterUpdateComponent>;
        let service: DivisionMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [DivisionMasterUpdateComponent]
            })
                .overrideTemplate(DivisionMasterUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DivisionMasterUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DivisionMasterService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new DivisionMaster('9fec3727-3421-4967-b213-ba36557ca194');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.divisionMaster = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new DivisionMaster();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.divisionMaster = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
