package me.emmarivera.weather

import dagger.Lazy
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import me.emmarivera.weather.internal.logging.ActivityLifecycleLogger
import me.emmarivera.weather.internal.logging.CrashlyticsTree
import me.emmarivera.weather.internal.logging.Loggable
import timber.log.Timber
import javax.inject.Inject

class WeatherApp : DaggerApplication(), Loggable {
  override val logTag: String = "weather-app"

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
    DaggerAppComponent.factory().create(this)

  @Inject lateinit var activityLifecycleLogger: ActivityLifecycleLogger
  @Inject lateinit var crashlyticsTree: Lazy<CrashlyticsTree>

  override fun onCreate() {
    initLogging()
    logger.i("onCreate()")

    super.onCreate()

    registerActivityLifecycleCallbacks(activityLifecycleLogger)
  }

  private fun initLogging() = Timber.plant(
    when {
      BuildConfig.DEBUG -> Timber.DebugTree()
      else -> crashlyticsTree.get()
    }
  )
}