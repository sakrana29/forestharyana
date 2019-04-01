/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { InchargeMasterTypeUpdateComponent } from 'app/entities/incharge-master-type/incharge-master-type-update.component';
import { InchargeMasterTypeService } from 'app/entities/incharge-master-type/incharge-master-type.service';
import { InchargeMasterType } from 'app/shared/model/incharge-master-type.model';

describe('Component Tests', () => {
    describe('InchargeMasterType Management Update Component', () => {
        let comp: InchargeMasterTypeUpdateComponent;
        let fixture: ComponentFixture<InchargeMasterTypeUpdateComponent>;
        let service: InchargeMasterTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [InchargeMasterTypeUpdateComponent]
            })
                .overrideTemplate(InchargeMasterTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InchargeMasterTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InchargeMasterTypeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new InchargeMasterType('9fec3727-3421-4967-b213-ba36557ca194');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.inchargeMasterType = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new InchargeMasterType();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.inchargeMasterType = entity;
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
