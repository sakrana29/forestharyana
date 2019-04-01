/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { RangeMasterDetailComponent } from 'app/entities/range-master/range-master-detail.component';
import { RangeMaster } from 'app/shared/model/range-master.model';

describe('Component Tests', () => {
    describe('RangeMaster Management Detail Component', () => {
        let comp: RangeMasterDetailComponent;
        let fixture: ComponentFixture<RangeMasterDetailComponent>;
        const route = ({ data: of({ rangeMaster: new RangeMaster('9fec3727-3421-4967-b213-ba36557ca194') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [RangeMasterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RangeMasterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RangeMasterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.rangeMaster).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
            });
        });
    });
});
