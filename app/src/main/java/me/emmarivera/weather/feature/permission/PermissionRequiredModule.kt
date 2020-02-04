package me.emmarivera.weather.feature.permission

import dagger.Binds
import dagger.Module
import me.emmarivera.weather.internal.ActivityScope

@Module
abstract class PermissionRequiredModule {

  @Binds
  @ActivityScope
  abstract fun presenter(impl: PermissionRequiredPresenterImpl): PermissionRequiredPresenter

  @Binds
  @ActivityScope
  abstract fun view(impl: PermissionRequiredActivity): PermissionRequiredView
}