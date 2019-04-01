/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { InchargeMasterTypeDetailComponent } from 'app/entities/incharge-master-type/incharge-master-type-detail.component';
import { InchargeMasterType } from 'app/shared/model/incharge-master-type.model';

describe('Component Tests', () => {
    describe('InchargeMasterType Management Detail Component', () => {
        let comp: InchargeMasterTypeDetailComponent;
        let fixture: ComponentFixture<InchargeMasterTypeDetailComponent>;
        const route = ({
            data: of({ inchargeMasterType: new InchargeMasterType('9fec3727-3421-4967-b213-ba36557ca194') })
        } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [InchargeMasterTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(InchargeMasterTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InchargeMasterTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.inchargeMasterType).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
            });
        });
    });
});
