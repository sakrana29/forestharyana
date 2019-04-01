import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INurseryMaster } from 'app/shared/model/nursery-master.model';

type EntityResponseType = HttpResponse<INurseryMaster>;
type EntityArrayResponseType = HttpResponse<INurseryMaster[]>;

@Injectable({ providedIn: 'root' })
export class NurseryMasterService {
    public resourceUrl = SERVER_API_URL + 'api/nursery-masters';

    constructor(protected http: HttpClient) {}

    create(nurseryMaster: INurseryMaster): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nurseryMaster);
        return this.http
            .post<INurseryMaster>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(nurseryMaster: INurseryMaster): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nurseryMaster);
        return this.http
            .put<INurseryMaster>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http
            .get<INurseryMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INurseryMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(nurseryMaster: INurseryMaster): INurseryMaster {
        const copy: INurseryMaster = Object.assign({}, nurseryMaster, {
            nurseryEstablishment:
                nurseryMaster.nurseryEstablishment != null && nurseryMaster.nurseryEstablishment.isValid()
                    ? nurseryMaster.nurseryEstablishment.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.nurseryEstablishment = res.body.nurseryEstablishment != null ? moment(res.body.nurseryEstablishment) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((nurseryMaster: INurseryMaster) => {
                nurseryMaster.nurseryEstablishment =
                    nurseryMaster.nurseryEstablishment != null ? moment(nurseryMaster.nurseryEstablishment) : null;
            });
        }
        return res;
    }
}
