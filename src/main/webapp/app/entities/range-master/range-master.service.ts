import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRangeMaster } from 'app/shared/model/range-master.model';

type EntityResponseType = HttpResponse<IRangeMaster>;
type EntityArrayResponseType = HttpResponse<IRangeMaster[]>;

@Injectable({ providedIn: 'root' })
export class RangeMasterService {
    public resourceUrl = SERVER_API_URL + 'api/range-masters';

    constructor(protected http: HttpClient) {}

    create(rangeMaster: IRangeMaster): Observable<EntityResponseType> {
        return this.http.post<IRangeMaster>(this.resourceUrl, rangeMaster, { observe: 'response' });
    }

    update(rangeMaster: IRangeMaster): Observable<EntityResponseType> {
        return this.http.put<IRangeMaster>(this.resourceUrl, rangeMaster, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IRangeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRangeMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
