package me.emmarivera.weather.feature.home

import dagger.Binds
import dagger.Module
import me.emmarivera.weather.feature.home.presenter.HomePresenter
import me.emmarivera.weather.feature.home.presenter.HomePresenterImpl
import me.emmarivera.weather.feature.home.view.HomeActivity
import me.emmarivera.weather.feature.home.view.HomeView
import me.emmarivera.weather.internal.ActivityScope


@Module
abstract class HomeModule {

    @Binds
    @ActivityScope
    abstract fun presenter(impl: HomePresenterImpl): HomePresenter

    @Binds
    @ActivityScope
    abstract fun view(impl: HomeActivity): HomeView
}