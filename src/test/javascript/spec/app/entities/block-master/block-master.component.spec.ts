/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ForestharyanaTestModule } from '../../../test.module';
import { BlockMasterComponent } from 'app/entities/block-master/block-master.component';
import { BlockMasterService } from 'app/entities/block-master/block-master.service';
import { BlockMaster } from 'app/shared/model/block-master.model';

describe('Component Tests', () => {
    describe('BlockMaster Management Component', () => {
        let comp: BlockMasterComponent;
        let fixture: ComponentFixture<BlockMasterComponent>;
        let service: BlockMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [BlockMasterComponent],
                providers: []
            })
                .overrideTemplate(BlockMasterComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BlockMasterComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BlockMasterService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new BlockMaster('9fec3727-3421-4967-b213-ba36557ca194')],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.blockMasters[0]).toEqual(jasmine.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
        });
    });
});
