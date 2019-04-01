/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ForestharyanaTestModule } from '../../../test.module';
import { InchargeMasterComponent } from 'app/entities/incharge-master/incharge-master.component';
import { InchargeMasterService } from 'app/entities/incharge-master/incharge-master.service';
import { InchargeMaster } from 'app/shared/model/incharge-master.model';

describe('Component Tests', () => {
    describe('InchargeMaster Management Component', () => {
        let comp: InchargeMasterComponent;
        let fixture: ComponentFixture<InchargeMasterComponent>;
        let service: InchargeMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [InchargeMasterComponent],
                providers: []
            })
                .overrideTemplate(InchargeMasterComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InchargeMasterComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InchargeMasterService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new InchargeMaster('9fec3727-3421-4967-b213-ba36557ca194')],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.inchargeMasters[0]).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
        });
    });
});
