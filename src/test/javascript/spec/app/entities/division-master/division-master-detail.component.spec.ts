/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { DivisionMasterDetailComponent } from 'app/entities/division-master/division-master-detail.component';
import { DivisionMaster } from 'app/shared/model/division-master.model';

describe('Component Tests', () => {
    describe('DivisionMaster Management Detail Component', () => {
        let comp: DivisionMasterDetailComponent;
        let fixture: ComponentFixture<DivisionMasterDetailComponent>;
        const route = ({
            data: of({ divisionMaster: new DivisionMaster('9fec3727-3421-4967-b213-ba36557ca194') })
        } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [DivisionMasterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DivisionMasterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DivisionMasterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.divisionMaster).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
            });
        });
    });
});
