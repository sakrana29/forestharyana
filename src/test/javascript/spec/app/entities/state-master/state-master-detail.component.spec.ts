/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { StateMasterDetailComponent } from 'app/entities/state-master/state-master-detail.component';
import { StateMaster } from 'app/shared/model/state-master.model';

describe('Component Tests', () => {
    describe('StateMaster Management Detail Component', () => {
        let comp: StateMasterDetailComponent;
        let fixture: ComponentFixture<StateMasterDetailComponent>;
        const route = ({ data: of({ stateMaster: new StateMaster('9fec3727-3421-4967-b213-ba36557ca194') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [StateMasterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StateMasterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StateMasterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.stateMaster).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
            });
        });
    });
});
