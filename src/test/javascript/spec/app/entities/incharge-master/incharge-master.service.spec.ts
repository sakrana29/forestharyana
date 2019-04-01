/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { InchargeMasterService } from 'app/entities/incharge-master/incharge-master.service';
import { IInchargeMaster, InchargeMaster } from 'app/shared/model/incharge-master.model';

describe('Service Tests', () => {
    describe('InchargeMaster Service', () => {
        let injector: TestBed;
        let service: InchargeMasterService;
        let httpMock: HttpTestingController;
        let elemDefault: IInchargeMaster;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(InchargeMasterService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new InchargeMaster(
                'ID',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                currentDate,
                false
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        chargeTakenFrom: currentDate.format(DATE_TIME_FORMAT),
                        chargeReleaveDate: currentDate.format(DATE_TIME_FORMAT)
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

            it('should create a InchargeMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 'ID',
                        chargeTakenFrom: currentDate.format(DATE_TIME_FORMAT),
                        chargeReleaveDate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        chargeTakenFrom: currentDate,
                        chargeReleaveDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new InchargeMaster(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a InchargeMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        inchargeMasterType: 'BBBBBB',
                        inchargeMasterTypeId: 'BBBBBB',
                        nurseryName: 'BBBBBB',
                        nurseryId: 'BBBBBB',
                        inchargeName: 'BBBBBB',
                        inchargeDesignation: 'BBBBBB',
                        inchargeMobile: 'BBBBBB',
                        chargeTakenFrom: currentDate.format(DATE_TIME_FORMAT),
                        chargeReleaveDate: currentDate.format(DATE_TIME_FORMAT),
                        isActive: true
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        chargeTakenFrom: currentDate,
                        chargeReleaveDate: currentDate
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

            it('should return a list of InchargeMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        inchargeMasterType: 'BBBBBB',
                        inchargeMasterTypeId: 'BBBBBB',
                        nurseryName: 'BBBBBB',
                        nurseryId: 'BBBBBB',
                        inchargeName: 'BBBBBB',
                        inchargeDesignation: 'BBBBBB',
                        inchargeMobile: 'BBBBBB',
                        chargeTakenFrom: currentDate.format(DATE_TIME_FORMAT),
                        chargeReleaveDate: currentDate.format(DATE_TIME_FORMAT),
                        isActive: true
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        chargeTakenFrom: currentDate,
                        chargeReleaveDate: currentDate
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

            it('should delete a InchargeMaster', async () => {
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
