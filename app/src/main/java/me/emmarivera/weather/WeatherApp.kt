package me.emmarivera.weather

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class WeatherApp : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication>
      = DaggerAppComponent.factory().create(this)

  override fun onCreate() {
    // Setup logging before injection
    initTimber()
    super.onCreate()
  }

  private fun initTimber() {
    if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
  }
}