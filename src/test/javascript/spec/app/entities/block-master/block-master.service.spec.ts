/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { BlockMasterService } from 'app/entities/block-master/block-master.service';
import { IBlockMaster, BlockMaster } from 'app/shared/model/block-master.model';

describe('Service Tests', () => {
    describe('BlockMaster Service', () => {
        let injector: TestBed;
        let service: BlockMasterService;
        let httpMock: HttpTestingController;
        let elemDefault: IBlockMaster;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(BlockMasterService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new BlockMaster(
                'ID',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                false
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find('9fec3727-3421-4967-b213-ba36557ca194')
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a BlockMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 'ID'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new BlockMaster(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a BlockMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        blockName: 'BBBBBB',
                        rangeName: 'BBBBBB',
                        rangeId: 'BBBBBB',
                        divisionName: 'BBBBBB',
                        divisionId: 'BBBBBB',
                        circleName: 'BBBBBB',
                        circleId: 'BBBBBB',
                        stateName: 'BBBBBB',
                        stateId: 'BBBBBB',
                        isActive: true
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of BlockMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        blockName: 'BBBBBB',
                        rangeName: 'BBBBBB',
                        rangeId: 'BBBBBB',
                        divisionName: 'BBBBBB',
                        divisionId: 'BBBBBB',
                        circleName: 'BBBBBB',
                        circleId: 'BBBBBB',
                        stateName: 'BBBBBB',
                        stateId: 'BBBBBB',
                        isActive: true
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a BlockMaster', async () => {
                const rxPromise = service.delete('9fec3727-3421-4967-b213-ba36557ca194').subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
