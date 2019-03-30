/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { RangeMasterUpdateComponent } from 'app/entities/range-master/range-master-update.component';
import { RangeMasterService } from 'app/entities/range-master/range-master.service';
import { RangeMaster } from 'app/shared/model/range-master.model';

describe('Component Tests', () => {
    describe('RangeMaster Management Update Component', () => {
        let comp: RangeMasterUpdateComponent;
        let fixture: ComponentFixture<RangeMasterUpdateComponent>;
        let service: RangeMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [RangeMasterUpdateComponent]
            })
                .overrideTemplate(RangeMasterUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RangeMasterUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RangeMasterService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RangeMaster('9fec3727-3421-4967-b213-ba36557ca194');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.rangeMaster = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RangeMaster();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.rangeMaster = entity;
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
