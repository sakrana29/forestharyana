/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { NurseryMasterDetailComponent } from 'app/entities/nursery-master/nursery-master-detail.component';
import { NurseryMaster } from 'app/shared/model/nursery-master.model';

describe('Component Tests', () => {
    describe('NurseryMaster Management Detail Component', () => {
        let comp: NurseryMasterDetailComponent;
        let fixture: ComponentFixture<NurseryMasterDetailComponent>;
        const route = ({ data: of({ nurseryMaster: new NurseryMaster('9fec3727-3421-4967-b213-ba36557ca194') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [NurseryMasterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NurseryMasterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NurseryMasterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.nurseryMaster).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
            });
        });
    });
});
