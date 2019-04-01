import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RangeMaster } from 'app/shared/model/range-master.model';
import { RangeMasterService } from './range-master.service';
import { RangeMasterComponent } from './range-master.component';
import { RangeMasterDetailComponent } from './range-master-detail.component';
import { RangeMasterUpdateComponent } from './range-master-update.component';
import { RangeMasterDeletePopupComponent } from './range-master-delete-dialog.component';
import { IRangeMaster } from 'app/shared/model/range-master.model';

@Injectable({ providedIn: 'root' })
export class RangeMasterResolve implements Resolve<IRangeMaster> {
    constructor(private service: RangeMasterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RangeMaster> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RangeMaster>) => response.ok),
                map((rangeMaster: HttpResponse<RangeMaster>) => rangeMaster.body)
            );
        }
        return of(new RangeMaster());
    }
}

export const rangeMasterRoute: Routes = [
    {
        path: 'range-master',
        component: RangeMasterComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.rangeMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'range-master/:id/view',
        component: RangeMasterDetailComponent,
        resolve: {
            rangeMaster: RangeMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.rangeMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'range-master/new',
        component: RangeMasterUpdateComponent,
        resolve: {
            rangeMaster: RangeMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.rangeMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'range-master/:id/edit',
        component: RangeMasterUpdateComponent,
        resolve: {
            rangeMaster: RangeMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.rangeMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rangeMasterPopupRoute: Routes = [
    {
        path: 'range-master/:id/delete',
        component: RangeMasterDeletePopupComponent,
        resolve: {
            rangeMaster: RangeMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.rangeMaster.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
