import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { InchargeMaster } from 'app/shared/model/incharge-master.model';
import { InchargeMasterService } from './incharge-master.service';
import { InchargeMasterComponent } from './incharge-master.component';
import { InchargeMasterDetailComponent } from './incharge-master-detail.component';
import { InchargeMasterUpdateComponent } from './incharge-master-update.component';
import { InchargeMasterDeletePopupComponent } from './incharge-master-delete-dialog.component';
import { IInchargeMaster } from 'app/shared/model/incharge-master.model';

@Injectable({ providedIn: 'root' })
export class InchargeMasterResolve implements Resolve<IInchargeMaster> {
    constructor(private service: InchargeMasterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<InchargeMaster> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<InchargeMaster>) => response.ok),
                map((inchargeMaster: HttpResponse<InchargeMaster>) => inchargeMaster.body)
            );
        }
        return of(new InchargeMaster());
    }
}

export const inchargeMasterRoute: Routes = [
    {
        path: 'incharge-master',
        component: InchargeMasterComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.inchargeMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'incharge-master/:id/view',
        component: InchargeMasterDetailComponent,
        resolve: {
            inchargeMaster: InchargeMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.inchargeMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'incharge-master/new',
        component: InchargeMasterUpdateComponent,
        resolve: {
            inchargeMaster: InchargeMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.inchargeMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'incharge-master/:id/edit',
        component: InchargeMasterUpdateComponent,
        resolve: {
            inchargeMaster: InchargeMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.inchargeMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const inchargeMasterPopupRoute: Routes = [
    {
        path: 'incharge-master/:id/delete',
        component: InchargeMasterDeletePopupComponent,
        resolve: {
            inchargeMaster: InchargeMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.inchargeMaster.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
