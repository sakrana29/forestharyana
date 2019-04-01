/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ForestharyanaTestModule } from '../../../test.module';
import { BeatMasterComponent } from 'app/entities/beat-master/beat-master.component';
import { BeatMasterService } from 'app/entities/beat-master/beat-master.service';
import { BeatMaster } from 'app/shared/model/beat-master.model';

describe('Component Tests', () => {
    describe('BeatMaster Management Component', () => {
        let comp: BeatMasterComponent;
        let fixture: ComponentFixture<BeatMasterComponent>;
        let service: BeatMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [BeatMasterComponent],
                providers: []
            })
                .overrideTemplate(BeatMasterComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BeatMasterComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BeatMasterService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new BeatMaster('9fec3727-3421-4967-b213-ba36557ca194')],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.beatMasters[0]).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
        });
    });
});
