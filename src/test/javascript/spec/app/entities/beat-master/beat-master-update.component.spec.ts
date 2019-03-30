/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { BeatMasterUpdateComponent } from 'app/entities/beat-master/beat-master-update.component';
import { BeatMasterService } from 'app/entities/beat-master/beat-master.service';
import { BeatMaster } from 'app/shared/model/beat-master.model';

describe('Component Tests', () => {
    describe('BeatMaster Management Update Component', () => {
        let comp: BeatMasterUpdateComponent;
        let fixture: ComponentFixture<BeatMasterUpdateComponent>;
        let service: BeatMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [BeatMasterUpdateComponent]
            })
                .overrideTemplate(BeatMasterUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BeatMasterUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BeatMasterService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new BeatMaster('9fec3727-3421-4967-b213-ba36557ca194');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.beatMaster = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new BeatMaster();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.beatMaster = entity;
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
