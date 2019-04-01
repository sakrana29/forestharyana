/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ForestharyanaTestModule } from '../../../test.module';
import { NurseryMasterComponent } from 'app/entities/nursery-master/nursery-master.component';
import { NurseryMasterService } from 'app/entities/nursery-master/nursery-master.service';
import { NurseryMaster } from 'app/shared/model/nursery-master.model';

describe('Component Tests', () => {
    describe('NurseryMaster Management Component', () => {
        let comp: NurseryMasterComponent;
        let fixture: ComponentFixture<NurseryMasterComponent>;
        let service: NurseryMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [NurseryMasterComponent],
                providers: []
            })
                .overrideTemplate(NurseryMasterComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NurseryMasterComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NurseryMasterService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new NurseryMaster('9fec3727-3421-4967-b213-ba36557ca194')],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.nurseryMasters[0]).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
        });
    });
});
