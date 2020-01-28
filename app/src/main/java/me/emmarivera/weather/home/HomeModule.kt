package me.emmarivera.weather.home

import dagger.Binds
import dagger.Module
import me.emmarivera.weather.internal.ActivityScope

@Module
abstract class HomeModule {

  @Binds
  @ActivityScope
  abstract fun presenter(impl: HomePrenterImpl): HomePresenter

  @Binds
  @ActivityScope
  abstract fun view(impl: HomeActivity): HomeView
}