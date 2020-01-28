package me.emmarivera.weather

import android.app.Application
import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.emmarivera.weather.data.DataModule
import me.emmarivera.weather.feature.HomeActivity
import me.emmarivera.weather.internal.ActivityScope
import me.emmarivera.weather.internal.AppScope
import me.emmarivera.weather.feature.splash.SplashModule
import me.emmarivera.weather.feature.splash.view.SplashActivity

/**
 * Tells Dagger2 how to inject application specific items the @AppScope
 */
@Module
abstract class AppModule {

  @Binds
  @AppScope
  abstract fun application(app: WeatherApp): Application

  @Binds
  @AppScope
  abstract fun appContext(app: WeatherApp): Context
}

/**
 * Tells Dagger2 how to inject all the Firebase classes we use.
 */
@Module
class FirebaseModule {
  @Provides
  @AppScope
  fun crashlytics(): FirebaseCrashlytics = FirebaseCrashlytics.getInstance()

  @Provides
  @AppScope
  fun analytics(context: Context): FirebaseAnalytics = FirebaseAnalytics.getInstance(context)
}

/**
 * Tells Dagger2 to hook-up injection *magically* for our activities
 */
@Module
abstract class ActivityInjectorModule {

  @ActivityScope
  @ContributesAndroidInjector(modules = [SplashModule::class])
  abstract fun splashActivity(): SplashActivity

  @ActivityScope
  @ContributesAndroidInjector
  abstract fun homeActivity(): HomeActivity
}

/**
 * Tells Dagger2 what modules are used in our application scope.
 */
@AppScope
@Component(modules = [
  AndroidInjectionModule::class,
  AndroidSupportInjectionModule::class,
  AppModule::class,
  ActivityInjectorModule::class,
  FirebaseModule::class,
  DataModule::class
])
interface AppComponent : AndroidInjector<WeatherApp> {

  @Component.Factory
  interface Factory : AndroidInjector.Factory<WeatherApp>
}