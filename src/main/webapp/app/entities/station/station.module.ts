import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VirtaSharedModule } from 'app/shared';
import {
    StationComponent,
    StationDetailComponent,
    StationUpdateComponent,
    StationDeletePopupComponent,
    StationDeleteDialogComponent,
    stationRoute,
    stationPopupRoute
} from './';

const ENTITY_STATES = [...stationRoute, ...stationPopupRoute];

@NgModule({
    imports: [VirtaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StationComponent,
        StationDetailComponent,
        StationUpdateComponent,
        StationDeleteDialogComponent,
        StationDeletePopupComponent
    ],
    entryComponents: [StationComponent, StationUpdateComponent, StationDeleteDialogComponent, StationDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VirtaStationModule {}
