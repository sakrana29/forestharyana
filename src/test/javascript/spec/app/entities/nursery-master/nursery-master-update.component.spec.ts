/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { NurseryMasterUpdateComponent } from 'app/entities/nursery-master/nursery-master-update.component';
import { NurseryMasterService } from 'app/entities/nursery-master/nursery-master.service';
import { NurseryMaster } from 'app/shared/model/nursery-master.model';

describe('Component Tests', () => {
    describe('NurseryMaster Management Update Component', () => {
        let comp: NurseryMasterUpdateComponent;
        let fixture: ComponentFixture<NurseryMasterUpdateComponent>;
        let service: NurseryMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [NurseryMasterUpdateComponent]
            })
                .overrideTemplate(NurseryMasterUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NurseryMasterUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NurseryMasterService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new NurseryMaster('9fec3727-3421-4967-b213-ba36557ca194');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.nurseryMaster = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new NurseryMaster();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.nurseryMaster = entity;
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
