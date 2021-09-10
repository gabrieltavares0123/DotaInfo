package com.magrathea.hero_interactor

import com.magrathea.hero_datasource.network.HeroService

data class HeroInteractors(
    val getHeros: GetHeros,
    // TODO: Add more interactors here.
) {
    companion object Factory {
        fun build(): HeroInteractors {
            val heroService = HeroService.build()

            return HeroInteractors(
                getHeros = GetHeros(
                    heroService
                )
            )
        }
    }
}