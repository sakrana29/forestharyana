import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICircleMaster } from 'app/shared/model/circle-master.model';

type EntityResponseType = HttpResponse<ICircleMaster>;
type EntityArrayResponseType = HttpResponse<ICircleMaster[]>;

@Injectable({ providedIn: 'root' })
export class CircleMasterService {
    public resourceUrl = SERVER_API_URL + 'api/circle-masters';

    constructor(protected http: HttpClient) {}

    create(circleMaster: ICircleMaster): Observable<EntityResponseType> {
        return this.http.post<ICircleMaster>(this.resourceUrl, circleMaster, { observe: 'response' });
    }

    update(circleMaster: ICircleMaster): Observable<EntityResponseType> {
        return this.http.put<ICircleMaster>(this.resourceUrl, circleMaster, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<ICircleMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICircleMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
