import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BlockMaster } from 'app/shared/model/block-master.model';
import { BlockMasterService } from './block-master.service';
import { BlockMasterComponent } from './block-master.component';
import { BlockMasterDetailComponent } from './block-master-detail.component';
import { BlockMasterUpdateComponent } from './block-master-update.component';
import { BlockMasterDeletePopupComponent } from './block-master-delete-dialog.component';
import { IBlockMaster } from 'app/shared/model/block-master.model';

@Injectable({ providedIn: 'root' })
export class BlockMasterResolve implements Resolve<IBlockMaster> {
    constructor(private service: BlockMasterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<BlockMaster> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<BlockMaster>) => response.ok),
                map((blockMaster: HttpResponse<BlockMaster>) => blockMaster.body)
            );
        }
        return of(new BlockMaster());
    }
}

export const blockMasterRoute: Routes = [
    {
        path: 'block-master',
        component: BlockMasterComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.blockMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'block-master/:id/view',
        component: BlockMasterDetailComponent,
        resolve: {
            blockMaster: BlockMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.blockMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'block-master/new',
        component: BlockMasterUpdateComponent,
        resolve: {
            blockMaster: BlockMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.blockMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'block-master/:id/edit',
        component: BlockMasterUpdateComponent,
        resolve: {
            blockMaster: BlockMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.blockMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const blockMasterPopupRoute: Routes = [
    {
        path: 'block-master/:id/delete',
        component: BlockMasterDeletePopupComponent,
        resolve: {
            blockMaster: BlockMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'forestharyanaApp.blockMaster.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
