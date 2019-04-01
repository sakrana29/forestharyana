/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { InchargeMasterUpdateComponent } from 'app/entities/incharge-master/incharge-master-update.component';
import { InchargeMasterService } from 'app/entities/incharge-master/incharge-master.service';
import { InchargeMaster } from 'app/shared/model/incharge-master.model';

describe('Component Tests', () => {
    describe('InchargeMaster Management Update Component', () => {
        let comp: InchargeMasterUpdateComponent;
        let fixture: ComponentFixture<InchargeMasterUpdateComponent>;
        let service: InchargeMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [InchargeMasterUpdateComponent]
            })
                .overrideTemplate(InchargeMasterUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InchargeMasterUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InchargeMasterService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new InchargeMaster('9fec3727-3421-4967-b213-ba36557ca194');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.inchargeMaster = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new InchargeMaster();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.inchargeMaster = entity;
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
