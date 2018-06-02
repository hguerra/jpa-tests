import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PokemonSharedModule } from '../../shared';
import {
    PokemonService,
    PokemonPopupService,
    PokemonComponent,
    PokemonDetailComponent,
    PokemonDialogComponent,
    PokemonPopupComponent,
    PokemonDeletePopupComponent,
    PokemonDeleteDialogComponent,
    pokemonRoute,
    pokemonPopupRoute,
    PokemonResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...pokemonRoute,
    ...pokemonPopupRoute,
];

@NgModule({
    imports: [
        PokemonSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PokemonComponent,
        PokemonDetailComponent,
        PokemonDialogComponent,
        PokemonDeleteDialogComponent,
        PokemonPopupComponent,
        PokemonDeletePopupComponent,
    ],
    entryComponents: [
        PokemonComponent,
        PokemonDialogComponent,
        PokemonPopupComponent,
        PokemonDeleteDialogComponent,
        PokemonDeletePopupComponent,
    ],
    providers: [
        PokemonService,
        PokemonPopupService,
        PokemonResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PokemonPokemonModule {}
