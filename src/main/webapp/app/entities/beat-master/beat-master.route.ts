import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BeatMaster } from 'app/shared/model/beat-master.model';
import { BeatMasterService } from './beat-master.service';
import { BeatMasterComponent } from './beat-master.component';
import { BeatMasterDetailComponent } from './beat-master-detail.component';
import { BeatMasterUpdateComponent } from './beat-master-update.component';
import { BeatMasterDeletePopupComponent } from './beat-master-delete-dialog.component';
import { IBeatMaster } from 'app/shared/model/beat-master.model';

@Injectable({ providedIn: 'root' })
export class BeatMasterResolve implements Resolve<IBeatMaster> {
    constructor(private service: BeatMasterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<BeatMaster> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<BeatMaster>) => response.ok),
                map((beatMaster: HttpResponse<BeatMaster>) => beatMaster.body)
            );
        }
        return of(new BeatMaster());
    }
}

export const beatMasterRoute: Routes = [
    {
        path: 'beat-master',
        component: BeatMasterComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.beatMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'beat-master/:id/view',
        component: BeatMasterDetailComponent,
        resolve: {
            beatMaster: BeatMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.beatMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'beat-master/new',
        component: BeatMasterUpdateComponent,
        resolve: {
            beatMaster: BeatMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.beatMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'beat-master/:id/edit',
        component: BeatMasterUpdateComponent,
        resolve: {
            beatMaster: BeatMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.beatMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const beatMasterPopupRoute: Routes = [
    {
        path: 'beat-master/:id/delete',
        component: BeatMasterDeletePopupComponent,
        resolve: {
            beatMaster: BeatMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.beatMaster.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
