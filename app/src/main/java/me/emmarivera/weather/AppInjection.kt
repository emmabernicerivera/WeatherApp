package me.emmarivera.weather

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.emmarivera.weather.internal.ActivityScope
import me.emmarivera.weather.internal.AppScope
import me.emmarivera.weather.feature.splash.SplashModule
import me.emmarivera.weather.feature.splash.view.SplashActivity

@Module
abstract class AppModule {

  @Binds
  @AppScope
  abstract fun application(app: WeatherApp): Application

  @Binds
  @AppScope
  abstract fun appContext(app: WeatherApp): Context
}

@Module
abstract class ActivityInjectorModule {

  @ActivityScope
  @ContributesAndroidInjector(modules = [SplashModule::class])
  abstract fun splashActivity(): SplashActivity
}

@AppScope
@Component(modules = [
  AndroidInjectionModule::class,
  AndroidSupportInjectionModule::class,
  AppModule::class,
  ActivityInjectorModule::class
])
interface AppComponent : AndroidInjector<WeatherApp> {

  @Component.Factory
  interface Factory : AndroidInjector.Factory<WeatherApp>
}