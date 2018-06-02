import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { PokemonComponent } from './pokemon.component';
import { PokemonDetailComponent } from './pokemon-detail.component';
import { PokemonPopupComponent } from './pokemon-dialog.component';
import { PokemonDeletePopupComponent } from './pokemon-delete-dialog.component';

@Injectable()
export class PokemonResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const pokemonRoute: Routes = [
    {
        path: 'pokemon',
        component: PokemonComponent,
        resolve: {
            'pagingParams': PokemonResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pokemons'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'pokemon/:id',
        component: PokemonDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pokemons'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pokemonPopupRoute: Routes = [
    {
        path: 'pokemon-new',
        component: PokemonPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pokemons'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pokemon/:id/edit',
        component: PokemonPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pokemons'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pokemon/:id/delete',
        component: PokemonDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pokemons'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
