import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IStation } from 'app/shared/model/station.model';
import { AccountService } from 'app/core';
import { StationService } from './station.service';

@Component({
    selector: 'jhi-station',
    templateUrl: './station.component.html'
})
export class StationComponent implements OnInit, OnDestroy {
    stations: IStation[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected stationService: StationService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.stationService
            .query()
            .pipe(
                filter((res: HttpResponse<IStation[]>) => res.ok),
                map((res: HttpResponse<IStation[]>) => res.body)
            )
            .subscribe(
                (res: IStation[]) => {
                    this.stations = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInStations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IStation) {
        return item.id;
    }

    registerChangeInStations() {
        this.eventSubscriber = this.eventManager.subscribe('stationListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
