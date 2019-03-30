import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStateMaster } from 'app/shared/model/state-master.model';

type EntityResponseType = HttpResponse<IStateMaster>;
type EntityArrayResponseType = HttpResponse<IStateMaster[]>;

@Injectable({ providedIn: 'root' })
export class StateMasterService {
    public resourceUrl = SERVER_API_URL + 'api/state-masters';

    constructor(protected http: HttpClient) {}

    create(stateMaster: IStateMaster): Observable<EntityResponseType> {
        return this.http.post<IStateMaster>(this.resourceUrl, stateMaster, { observe: 'response' });
    }

    update(stateMaster: IStateMaster): Observable<EntityResponseType> {
        return this.http.put<IStateMaster>(this.resourceUrl, stateMaster, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IStateMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IStateMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
