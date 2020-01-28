package me.emmarivera.weather.internal.logging

import android.app.Activity
import android.app.Application
import android.os.Bundle
import me.emmarivera.weather.internal.AppScope
import timber.log.Timber
import javax.inject.Inject

/**
 * Logs out the activity lifecycle events using timber.
 */
@AppScope
class ActivityLifecycleLogger @Inject constructor() : Application.ActivityLifecycleCallbacks {

  private fun log(activity: Activity, state: String) = when (activity) {
    is Loggable -> activity.logger.i(state)
    else -> Timber.tag(activity::class.java.simpleName).i(state)
  }

  override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) =
    log(activity, "onCreate()")

  override fun onActivityStarted(activity: Activity) = log(activity, "onStart()")
  override fun onActivityResumed(activity: Activity) = log(activity, "onResume()")
  override fun onActivityPaused(activity: Activity) = log(activity, "onPause()")
  override fun onActivityStopped(activity: Activity) = log(activity, "onStopped()")
  override fun onActivityDestroyed(activity: Activity) = log(activity, "onDestroyed()")

  override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) =
    log(activity, "onSaveInstanceState()")
}