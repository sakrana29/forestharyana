/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { RangeMasterService } from 'app/entities/range-master/range-master.service';
import { IRangeMaster, RangeMaster } from 'app/shared/model/range-master.model';

describe('Service Tests', () => {
    describe('RangeMaster Service', () => {
        let injector: TestBed;
        let service: RangeMasterService;
        let httpMock: HttpTestingController;
        let elemDefault: IRangeMaster;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(RangeMasterService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new RangeMaster('ID', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, 'AAAAAAA');
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

            it('should create a RangeMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 'ID'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new RangeMaster(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a RangeMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        rangeName: 'BBBBBB',
                        divisionNmae: 'BBBBBB',
                        divisionId: 'BBBBBB',
                        circleName: 'BBBBBB',
                        circleId: 'BBBBBB',
                        stateName: 'BBBBBB',
                        isActive: true,
                        stateId: 'BBBBBB'
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

            it('should return a list of RangeMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        rangeName: 'BBBBBB',
                        divisionNmae: 'BBBBBB',
                        divisionId: 'BBBBBB',
                        circleName: 'BBBBBB',
                        circleId: 'BBBBBB',
                        stateName: 'BBBBBB',
                        isActive: true,
                        stateId: 'BBBBBB'
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

            it('should delete a RangeMaster', async () => {
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
