/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { CircleMasterUpdateComponent } from 'app/entities/circle-master/circle-master-update.component';
import { CircleMasterService } from 'app/entities/circle-master/circle-master.service';
import { CircleMaster } from 'app/shared/model/circle-master.model';

describe('Component Tests', () => {
    describe('CircleMaster Management Update Component', () => {
        let comp: CircleMasterUpdateComponent;
        let fixture: ComponentFixture<CircleMasterUpdateComponent>;
        let service: CircleMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [CircleMasterUpdateComponent]
            })
                .overrideTemplate(CircleMasterUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CircleMasterUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CircleMasterService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CircleMaster('9fec3727-3421-4967-b213-ba36557ca194');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.circleMaster = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CircleMaster();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.circleMaster = entity;
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
