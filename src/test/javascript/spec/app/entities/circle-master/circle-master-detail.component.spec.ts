/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { CircleMasterDetailComponent } from 'app/entities/circle-master/circle-master-detail.component';
import { CircleMaster } from 'app/shared/model/circle-master.model';

describe('Component Tests', () => {
    describe('CircleMaster Management Detail Component', () => {
        let comp: CircleMasterDetailComponent;
        let fixture: ComponentFixture<CircleMasterDetailComponent>;
        const route = ({ data: of({ circleMaster: new CircleMaster('9fec3727-3421-4967-b213-ba36557ca194') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [CircleMasterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CircleMasterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CircleMasterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.circleMaster).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
            });
        });
    });
});
