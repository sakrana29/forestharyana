import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInchargeMaster } from 'app/shared/model/incharge-master.model';

type EntityResponseType = HttpResponse<IInchargeMaster>;
type EntityArrayResponseType = HttpResponse<IInchargeMaster[]>;

@Injectable({ providedIn: 'root' })
export class InchargeMasterService {
    public resourceUrl = SERVER_API_URL + 'api/incharge-masters';

    constructor(protected http: HttpClient) {}

    create(inchargeMaster: IInchargeMaster): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(inchargeMaster);
        return this.http
            .post<IInchargeMaster>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(inchargeMaster: IInchargeMaster): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(inchargeMaster);
        return this.http
            .put<IInchargeMaster>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http
            .get<IInchargeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IInchargeMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(inchargeMaster: IInchargeMaster): IInchargeMaster {
        const copy: IInchargeMaster = Object.assign({}, inchargeMaster, {
            chargeTakenFrom:
                inchargeMaster.chargeTakenFrom != null && inchargeMaster.chargeTakenFrom.isValid()
                    ? inchargeMaster.chargeTakenFrom.toJSON()
                    : null,
            chargeReleaveDate:
                inchargeMaster.chargeReleaveDate != null && inchargeMaster.chargeReleaveDate.isValid()
                    ? inchargeMaster.chargeReleaveDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.chargeTakenFrom = res.body.chargeTakenFrom != null ? moment(res.body.chargeTakenFrom) : null;
            res.body.chargeReleaveDate = res.body.chargeReleaveDate != null ? moment(res.body.chargeReleaveDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((inchargeMaster: IInchargeMaster) => {
                inchargeMaster.chargeTakenFrom = inchargeMaster.chargeTakenFrom != null ? moment(inchargeMaster.chargeTakenFrom) : null;
                inchargeMaster.chargeReleaveDate =
                    inchargeMaster.chargeReleaveDate != null ? moment(inchargeMaster.chargeReleaveDate) : null;
            });
        }
        return res;
    }
}
