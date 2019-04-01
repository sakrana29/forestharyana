/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ForestharyanaTestModule } from '../../../test.module';
import { CircleMasterComponent } from 'app/entities/circle-master/circle-master.component';
import { CircleMasterService } from 'app/entities/circle-master/circle-master.service';
import { CircleMaster } from 'app/shared/model/circle-master.model';

describe('Component Tests', () => {
    describe('CircleMaster Management Component', () => {
        let comp: CircleMasterComponent;
        let fixture: ComponentFixture<CircleMasterComponent>;
        let service: CircleMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [CircleMasterComponent],
                providers: []
            })
                .overrideTemplate(CircleMasterComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CircleMasterComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CircleMasterService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CircleMaster('9fec3727-3421-4967-b213-ba36557ca194')],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.circleMasters[0]).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
        });
    });
});
