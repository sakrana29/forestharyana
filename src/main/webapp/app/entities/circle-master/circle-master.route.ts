import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CircleMaster } from 'app/shared/model/circle-master.model';
import { CircleMasterService } from './circle-master.service';
import { CircleMasterComponent } from './circle-master.component';
import { CircleMasterDetailComponent } from './circle-master-detail.component';
import { CircleMasterUpdateComponent } from './circle-master-update.component';
import { CircleMasterDeletePopupComponent } from './circle-master-delete-dialog.component';
import { ICircleMaster } from 'app/shared/model/circle-master.model';

@Injectable({ providedIn: 'root' })
export class CircleMasterResolve implements Resolve<ICircleMaster> {
    constructor(private service: CircleMasterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CircleMaster> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CircleMaster>) => response.ok),
                map((circleMaster: HttpResponse<CircleMaster>) => circleMaster.body)
            );
        }
        return of(new CircleMaster());
    }
}

export const circleMasterRoute: Routes = [
    {
        path: 'circle-master',
        component: CircleMasterComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.circleMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'circle-master/:id/view',
        component: CircleMasterDetailComponent,
        resolve: {
            circleMaster: CircleMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.circleMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'circle-master/new',
        component: CircleMasterUpdateComponent,
        resolve: {
            circleMaster: CircleMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.circleMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'circle-master/:id/edit',
        component: CircleMasterUpdateComponent,
        resolve: {
            circleMaster: CircleMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.circleMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const circleMasterPopupRoute: Routes = [
    {
        path: 'circle-master/:id/delete',
        component: CircleMasterDeletePopupComponent,
        resolve: {
            circleMaster: CircleMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.circleMaster.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
