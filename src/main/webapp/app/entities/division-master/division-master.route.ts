import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DivisionMaster } from 'app/shared/model/division-master.model';
import { DivisionMasterService } from './division-master.service';
import { DivisionMasterComponent } from './division-master.component';
import { DivisionMasterDetailComponent } from './division-master-detail.component';
import { DivisionMasterUpdateComponent } from './division-master-update.component';
import { DivisionMasterDeletePopupComponent } from './division-master-delete-dialog.component';
import { IDivisionMaster } from 'app/shared/model/division-master.model';

@Injectable({ providedIn: 'root' })
export class DivisionMasterResolve implements Resolve<IDivisionMaster> {
    constructor(private service: DivisionMasterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DivisionMaster> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DivisionMaster>) => response.ok),
                map((divisionMaster: HttpResponse<DivisionMaster>) => divisionMaster.body)
            );
        }
        return of(new DivisionMaster());
    }
}

export const divisionMasterRoute: Routes = [
    {
        path: 'division-master',
        component: DivisionMasterComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.divisionMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'division-master/:id/view',
        component: DivisionMasterDetailComponent,
        resolve: {
            divisionMaster: DivisionMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.divisionMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'division-master/new',
        component: DivisionMasterUpdateComponent,
        resolve: {
            divisionMaster: DivisionMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.divisionMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'division-master/:id/edit',
        component: DivisionMasterUpdateComponent,
        resolve: {
            divisionMaster: DivisionMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.divisionMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const divisionMasterPopupRoute: Routes = [
    {
        path: 'division-master/:id/delete',
        component: DivisionMasterDeletePopupComponent,
        resolve: {
            divisionMaster: DivisionMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.divisionMaster.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
