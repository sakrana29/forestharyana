import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBlockMaster } from 'app/shared/model/block-master.model';

type EntityResponseType = HttpResponse<IBlockMaster>;
type EntityArrayResponseType = HttpResponse<IBlockMaster[]>;

@Injectable({ providedIn: 'root' })
export class BlockMasterService {
    public resourceUrl = SERVER_API_URL + 'api/block-masters';

    constructor(protected http: HttpClient) {}

    create(blockMaster: IBlockMaster): Observable<EntityResponseType> {
        return this.http.post<IBlockMaster>(this.resourceUrl, blockMaster, { observe: 'response' });
    }

    update(blockMaster: IBlockMaster): Observable<EntityResponseType> {
        return this.http.put<IBlockMaster>(this.resourceUrl, blockMaster, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IBlockMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBlockMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
