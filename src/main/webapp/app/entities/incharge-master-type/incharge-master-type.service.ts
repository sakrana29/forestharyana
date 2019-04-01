import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInchargeMasterType } from 'app/shared/model/incharge-master-type.model';

type EntityResponseType = HttpResponse<IInchargeMasterType>;
type EntityArrayResponseType = HttpResponse<IInchargeMasterType[]>;

@Injectable({ providedIn: 'root' })
export class InchargeMasterTypeService {
    public resourceUrl = SERVER_API_URL + 'api/incharge-master-types';

    constructor(protected http: HttpClient) {}

    create(inchargeMasterType: IInchargeMasterType): Observable<EntityResponseType> {
        return this.http.post<IInchargeMasterType>(this.resourceUrl, inchargeMasterType, { observe: 'response' });
    }

    update(inchargeMasterType: IInchargeMasterType): Observable<EntityResponseType> {
        return this.http.put<IInchargeMasterType>(this.resourceUrl, inchargeMasterType, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IInchargeMasterType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IInchargeMasterType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
