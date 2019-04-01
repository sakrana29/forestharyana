/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { BeatMasterDetailComponent } from 'app/entities/beat-master/beat-master-detail.component';
import { BeatMaster } from 'app/shared/model/beat-master.model';

describe('Component Tests', () => {
    describe('BeatMaster Management Detail Component', () => {
        let comp: BeatMasterDetailComponent;
        let fixture: ComponentFixture<BeatMasterDetailComponent>;
        const route = ({ data: of({ beatMaster: new BeatMaster('9fec3727-3421-4967-b213-ba36557ca194') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [BeatMasterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BeatMasterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BeatMasterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.beatMaster).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
            });
        });
    });
});
