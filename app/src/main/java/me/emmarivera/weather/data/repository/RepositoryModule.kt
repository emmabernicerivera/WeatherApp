package me.emmarivera.weather.data.repository

import dagger.Binds
import dagger.Module
import me.emmarivera.weather.internal.AppScope

@Module
abstract class RepositoryModule {
  @Binds
  @AppScope
  abstract fun permissionRepository(impl: PermissionRepositoryImpl): PermissionRepository
}