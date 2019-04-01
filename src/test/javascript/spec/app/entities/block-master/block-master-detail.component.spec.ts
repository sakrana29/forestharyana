/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { BlockMasterDetailComponent } from 'app/entities/block-master/block-master-detail.component';
import { BlockMaster } from 'app/shared/model/block-master.model';

describe('Component Tests', () => {
    describe('BlockMaster Management Detail Component', () => {
        let comp: BlockMasterDetailComponent;
        let fixture: ComponentFixture<BlockMasterDetailComponent>;
        const route = ({ data: of({ blockMaster: new BlockMaster('9fec3727-3421-4967-b213-ba36557ca194') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [BlockMasterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BlockMasterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BlockMasterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.blockMaster).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
            });
        });
    });
});
