/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { NurseryMasterService } from 'app/entities/nursery-master/nursery-master.service';
import { INurseryMaster, NurseryMaster } from 'app/shared/model/nursery-master.model';

describe('Service Tests', () => {
    describe('NurseryMaster Service', () => {
        let injector: TestBed;
        let service: NurseryMasterService;
        let httpMock: HttpTestingController;
        let elemDefault: INurseryMaster;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(NurseryMasterService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new NurseryMaster(
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
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        nurseryEstablishment: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find('9fec3727-3421-4967-b213-ba36557ca194')
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a NurseryMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 'ID',
                        nurseryEstablishment: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        nurseryEstablishment: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new NurseryMaster(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a NurseryMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        circleName: 'BBBBBB',
                        circleId: 'BBBBBB',
                        divisionName: 'BBBBBB',
                        divisionId: 'BBBBBB',
                        rangeName: 'BBBBBB',
                        rangeId: 'BBBBBB',
                        blockname: 'BBBBBB',
                        blockId: 'BBBBBB',
                        beatName: 'BBBBBB',
                        beatId: 'BBBBBB',
                        nurseryName: 'BBBBBB',
                        nurseryAddress: 'BBBBBB',
                        nurseryEstablishment: currentDate.format(DATE_TIME_FORMAT),
                        nurseryArea: 1,
                        sourceOfIrrigation: 'BBBBBB',
                        soilType: 'BBBBBB',
                        otherDetail: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        nurseryEstablishment: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of NurseryMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        circleName: 'BBBBBB',
                        circleId: 'BBBBBB',
                        divisionName: 'BBBBBB',
                        divisionId: 'BBBBBB',
                        rangeName: 'BBBBBB',
                        rangeId: 'BBBBBB',
                        blockname: 'BBBBBB',
                        blockId: 'BBBBBB',
                        beatName: 'BBBBBB',
                        beatId: 'BBBBBB',
                        nurseryName: 'BBBBBB',
                        nurseryAddress: 'BBBBBB',
                        nurseryEstablishment: currentDate.format(DATE_TIME_FORMAT),
                        nurseryArea: 1,
                        sourceOfIrrigation: 'BBBBBB',
                        soilType: 'BBBBBB',
                        otherDetail: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        nurseryEstablishment: currentDate
                    },
                    returnedFromService
                );
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

            it('should delete a NurseryMaster', async () => {
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
