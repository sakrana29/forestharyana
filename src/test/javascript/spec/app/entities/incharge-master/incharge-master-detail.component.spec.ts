/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { InchargeMasterDetailComponent } from 'app/entities/incharge-master/incharge-master-detail.component';
import { InchargeMaster } from 'app/shared/model/incharge-master.model';

describe('Component Tests', () => {
    describe('InchargeMaster Management Detail Component', () => {
        let comp: InchargeMasterDetailComponent;
        let fixture: ComponentFixture<InchargeMasterDetailComponent>;
        const route = ({
            data: of({ inchargeMaster: new InchargeMaster('9fec3727-3421-4967-b213-ba36557ca194') })
        } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [InchargeMasterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(InchargeMasterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InchargeMasterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.inchargeMaster).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
            });
        });
    });
});
