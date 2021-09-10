package com.magrathea.hero_interactor

import com.magrathea.core.DataState
import com.magrathea.core.ProgressBarState
import com.magrathea.core.UIComponent
import com.magrathea.hero_datasource.network.HeroService
import com.magrathea.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHeros(
    private val heroService: HeroService,
    // TODO: Add caching.
) {
    fun execute(): Flow<DataState<List<Hero>>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            val heros: List<Hero> = try {
                heroService.getHeroStats()
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            title = "Network rrror",
                            description = e.message ?: "Unknown error."
                        )
                    )
                )
                listOf()
            }

            emit(DataState.Data(heros))

            // TODO: Add caching.
        }
        catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response<List<Hero>>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown error."
                    )
                )
            )
        }
        finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}