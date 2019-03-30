/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ForestharyanaTestModule } from '../../../test.module';
import { DivisionMasterComponent } from 'app/entities/division-master/division-master.component';
import { DivisionMasterService } from 'app/entities/division-master/division-master.service';
import { DivisionMaster } from 'app/shared/model/division-master.model';

describe('Component Tests', () => {
    describe('DivisionMaster Management Component', () => {
        let comp: DivisionMasterComponent;
        let fixture: ComponentFixture<DivisionMasterComponent>;
        let service: DivisionMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [DivisionMasterComponent],
                providers: []
            })
                .overrideTemplate(DivisionMasterComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DivisionMasterComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DivisionMasterService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new DivisionMaster('9fec3727-3421-4967-b213-ba36557ca194')],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.divisionMasters[0]).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
        });
    });
});
