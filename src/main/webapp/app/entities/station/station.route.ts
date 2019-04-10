import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Station } from 'app/shared/model/station.model';
import { StationService } from './station.service';
import { StationComponent } from './station.component';
import { StationDetailComponent } from './station-detail.component';
import { StationUpdateComponent } from './station-update.component';
import { StationDeletePopupComponent } from './station-delete-dialog.component';
import { IStation } from 'app/shared/model/station.model';

@Injectable({ providedIn: 'root' })
export class StationResolve implements Resolve<IStation> {
    constructor(private service: StationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IStation> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Station>) => response.ok),
                map((station: HttpResponse<Station>) => station.body)
            );
        }
        return of(new Station());
    }
}

export const stationRoute: Routes = [
    {
        path: '',
        component: StationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Stations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: StationDetailComponent,
        resolve: {
            station: StationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Stations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: StationUpdateComponent,
        resolve: {
            station: StationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Stations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: StationUpdateComponent,
        resolve: {
            station: StationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Stations'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const stationPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: StationDeletePopupComponent,
        resolve: {
            station: StationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Stations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
