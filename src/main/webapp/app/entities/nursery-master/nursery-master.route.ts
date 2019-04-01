import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NurseryMaster } from 'app/shared/model/nursery-master.model';
import { NurseryMasterService } from './nursery-master.service';
import { NurseryMasterComponent } from './nursery-master.component';
import { NurseryMasterDetailComponent } from './nursery-master-detail.component';
import { NurseryMasterUpdateComponent } from './nursery-master-update.component';
import { NurseryMasterDeletePopupComponent } from './nursery-master-delete-dialog.component';
import { INurseryMaster } from 'app/shared/model/nursery-master.model';

@Injectable({ providedIn: 'root' })
export class NurseryMasterResolve implements Resolve<INurseryMaster> {
    constructor(private service: NurseryMasterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<NurseryMaster> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<NurseryMaster>) => response.ok),
                map((nurseryMaster: HttpResponse<NurseryMaster>) => nurseryMaster.body)
            );
        }
        return of(new NurseryMaster());
    }
}

export const nurseryMasterRoute: Routes = [
    {
        path: 'nursery-master',
        component: NurseryMasterComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.nurseryMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-master/:id/view',
        component: NurseryMasterDetailComponent,
        resolve: {
            nurseryMaster: NurseryMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.nurseryMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-master/new',
        component: NurseryMasterUpdateComponent,
        resolve: {
            nurseryMaster: NurseryMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.nurseryMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-master/:id/edit',
        component: NurseryMasterUpdateComponent,
        resolve: {
            nurseryMaster: NurseryMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.nurseryMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const nurseryMasterPopupRoute: Routes = [
    {
        path: 'nursery-master/:id/delete',
        component: NurseryMasterDeletePopupComponent,
        resolve: {
            nurseryMaster: NurseryMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.nurseryMaster.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
