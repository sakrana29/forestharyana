import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDivisionMaster } from 'app/shared/model/division-master.model';

type EntityResponseType = HttpResponse<IDivisionMaster>;
type EntityArrayResponseType = HttpResponse<IDivisionMaster[]>;

@Injectable({ providedIn: 'root' })
export class DivisionMasterService {
    public resourceUrl = SERVER_API_URL + 'api/division-masters';

    constructor(protected http: HttpClient) {}

    create(divisionMaster: IDivisionMaster): Observable<EntityResponseType> {
        return this.http.post<IDivisionMaster>(this.resourceUrl, divisionMaster, { observe: 'response' });
    }

    update(divisionMaster: IDivisionMaster): Observable<EntityResponseType> {
        return this.http.put<IDivisionMaster>(this.resourceUrl, divisionMaster, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IDivisionMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDivisionMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
