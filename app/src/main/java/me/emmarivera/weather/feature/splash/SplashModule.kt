package me.emmarivera.weather.feature.splash

import dagger.Binds
import dagger.Module
import me.emmarivera.weather.internal.ActivityScope
import me.emmarivera.weather.feature.splash.presenter.SplashPresenter
import me.emmarivera.weather.feature.splash.presenter.SplashPresenterImpl
import me.emmarivera.weather.feature.splash.view.SplashActivity
import me.emmarivera.weather.feature.splash.view.SplashView

@Module
abstract class SplashModule {

  @Binds
  @ActivityScope
  abstract fun presenter(impl: SplashPresenterImpl): SplashPresenter

  @Binds
  @ActivityScope
  abstract fun view(impl: SplashActivity): SplashView
}