/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ForestharyanaTestModule } from '../../../test.module';
import { InchargeMasterTypeComponent } from 'app/entities/incharge-master-type/incharge-master-type.component';
import { InchargeMasterTypeService } from 'app/entities/incharge-master-type/incharge-master-type.service';
import { InchargeMasterType } from 'app/shared/model/incharge-master-type.model';

describe('Component Tests', () => {
    describe('InchargeMasterType Management Component', () => {
        let comp: InchargeMasterTypeComponent;
        let fixture: ComponentFixture<InchargeMasterTypeComponent>;
        let service: InchargeMasterTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [InchargeMasterTypeComponent],
                providers: []
            })
                .overrideTemplate(InchargeMasterTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InchargeMasterTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InchargeMasterTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new InchargeMasterType('9fec3727-3421-4967-b213-ba36557ca194')],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.inchargeMasterTypes[0]).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
        });
    });
});
