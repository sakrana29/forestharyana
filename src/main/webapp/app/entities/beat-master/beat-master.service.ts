import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBeatMaster } from 'app/shared/model/beat-master.model';

type EntityResponseType = HttpResponse<IBeatMaster>;
type EntityArrayResponseType = HttpResponse<IBeatMaster[]>;

@Injectable({ providedIn: 'root' })
export class BeatMasterService {
    public resourceUrl = SERVER_API_URL + 'api/beat-masters';

    constructor(protected http: HttpClient) {}

    create(beatMaster: IBeatMaster): Observable<EntityResponseType> {
        return this.http.post<IBeatMaster>(this.resourceUrl, beatMaster, { observe: 'response' });
    }

    update(beatMaster: IBeatMaster): Observable<EntityResponseType> {
        return this.http.put<IBeatMaster>(this.resourceUrl, beatMaster, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IBeatMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBeatMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
