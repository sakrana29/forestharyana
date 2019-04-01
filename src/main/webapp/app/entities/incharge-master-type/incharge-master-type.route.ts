import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { InchargeMasterType } from 'app/shared/model/incharge-master-type.model';
import { InchargeMasterTypeService } from './incharge-master-type.service';
import { InchargeMasterTypeComponent } from './incharge-master-type.component';
import { InchargeMasterTypeDetailComponent } from './incharge-master-type-detail.component';
import { InchargeMasterTypeUpdateComponent } from './incharge-master-type-update.component';
import { InchargeMasterTypeDeletePopupComponent } from './incharge-master-type-delete-dialog.component';
import { IInchargeMasterType } from 'app/shared/model/incharge-master-type.model';

@Injectable({ providedIn: 'root' })
export class InchargeMasterTypeResolve implements Resolve<IInchargeMasterType> {
    constructor(private service: InchargeMasterTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<InchargeMasterType> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<InchargeMasterType>) => response.ok),
                map((inchargeMasterType: HttpResponse<InchargeMasterType>) => inchargeMasterType.body)
            );
        }
        return of(new InchargeMasterType());
    }
}

export const inchargeMasterTypeRoute: Routes = [
    {
        path: 'incharge-master-type',
        component: InchargeMasterTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.inchargeMasterType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'incharge-master-type/:id/view',
        component: InchargeMasterTypeDetailComponent,
        resolve: {
            inchargeMasterType: InchargeMasterTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.inchargeMasterType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'incharge-master-type/new',
        component: InchargeMasterTypeUpdateComponent,
        resolve: {
            inchargeMasterType: InchargeMasterTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.inchargeMasterType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'incharge-master-type/:id/edit',
        component: InchargeMasterTypeUpdateComponent,
        resolve: {
            inchargeMasterType: InchargeMasterTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.inchargeMasterType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const inchargeMasterTypePopupRoute: Routes = [
    {
        path: 'incharge-master-type/:id/delete',
        component: InchargeMasterTypeDeletePopupComponent,
        resolve: {
            inchargeMasterType: InchargeMasterTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.inchargeMasterType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
