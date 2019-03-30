/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ForestharyanaTestModule } from '../../../test.module';
import { BlockMasterUpdateComponent } from 'app/entities/block-master/block-master-update.component';
import { BlockMasterService } from 'app/entities/block-master/block-master.service';
import { BlockMaster } from 'app/shared/model/block-master.model';

describe('Component Tests', () => {
    describe('BlockMaster Management Update Component', () => {
        let comp: BlockMasterUpdateComponent;
        let fixture: ComponentFixture<BlockMasterUpdateComponent>;
        let service: BlockMasterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ForestharyanaTestModule],
                declarations: [BlockMasterUpdateComponent]
            })
                .overrideTemplate(BlockMasterUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BlockMasterUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BlockMasterService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new BlockMaster('9fec3727-3421-4967-b213-ba36557ca194');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.blockMaster = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new BlockMaster();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.blockMaster = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
