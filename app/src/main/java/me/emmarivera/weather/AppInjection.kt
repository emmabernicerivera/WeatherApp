package me.emmarivera.weather

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.emmarivera.weather.internal.AppScope

@Module
abstract class AppModule {

  @Binds
  @AppScope
  abstract fun application(app: WeatherApp): Application

  @Binds
  @AppScope
  abstract fun appContext(app: WeatherApp): Context
}

@AppScope
@Component(modules = [
  AppModule::class,
  AndroidInjectionModule::class,
  AndroidSupportInjectionModule::class
])
interface AppComponent : AndroidInjector<WeatherApp> {

  @Component.Factory
  interface Factory : AndroidInjector.Factory<WeatherApp>
}