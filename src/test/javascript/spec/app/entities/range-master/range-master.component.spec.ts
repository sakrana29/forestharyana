/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ForestharyanaTestModule } from '../../../test.module';
import { RangeMasterComponent } from 'app/entities/range-master/range-master.component';
import { RangeMasterService } from 'app/entities/range-master/range-master.service';
import { RangeMaster } from 'app/shared/model/range-master.model';

describe('Component Tests', () => {
    describe('RangeMaster Management Component', () => {
        let comp: RangeMasterComponent;
        let fixture: ComponentFixture<RangeMasterComponent>;
        let service: RangeMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [RangeMasterComponent],
                providers: []
            })
                .overrideTemplate(RangeMasterComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RangeMasterComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RangeMasterService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RangeMaster('9fec3727-3421-4967-b213-ba36557ca194')],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.rangeMasters[0]).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
        });
    });
});
