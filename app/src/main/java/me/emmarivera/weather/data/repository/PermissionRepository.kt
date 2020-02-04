package me.emmarivera.weather.data.repository

import android.Manifest
import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import me.emmarivera.weather.WeatherApp
import me.emmarivera.weather.internal.AppScope
import me.emmarivera.weather.internal.logging.Loggable
import javax.inject.Inject

/**
 * A simple-data class that represents a permission
 */
data class Permission(val id: String, val granted: Boolean)

/**
 * Our repository that defines how we observe permissions
 */
interface PermissionRepository {

  /**
   * An observable which emits true if all permissions are granted, and false otherwise.
   */
  val hasAllPermissions: Observable<Boolean>

  /**
   * An observable that emits the current permission state for the coarse-location permission
   */
  val location: Observable<Permission>
}

/**
 * Reflects the current state of all permissions.
 */
private data class State(
  val location: Permission
)

@AppScope
class PermissionRepositoryImpl @Inject constructor(
  private val app: WeatherApp
) : PermissionRepository, Loggable {
  override val logTag: String = "permission-repository"

  private val state: BehaviorRelay<State> = BehaviorRelay.createDefault(checkPermissionState())

  override val hasAllPermissions: Observable<Boolean> = state.map { state ->
    state.location.granted
  }

  override val location: Observable<Permission> = state.map { it.location }.distinctUntilChanged()

  init {
    // Update the permissions state on every activity resume.
    app.registerActivityLifecycleCallbacks(
      OnResumeCallback {
        val newState = checkPermissionState()
        logger.i("Checking permission state onResume(): $newState")
        state.accept(newState)
      }
    )
  }

  private fun isGranted(permission: String): Boolean =
    ContextCompat.checkSelfPermission(app, permission) == PackageManager.PERMISSION_GRANTED

  private fun checkPermissionState(): State = State(
    location = Permission(
      id = Manifest.permission.ACCESS_FINE_LOCATION,
      granted = isGranted(Manifest.permission.ACCESS_FINE_LOCATION)
    )
  )
}

private class OnResumeCallback(
  private val doOnResume: () -> Unit
) : ActivityLifecycleCallbacks {
  override fun onActivityPaused(activity: Activity) = Unit
  override fun onActivityStarted(activity: Activity) = Unit
  override fun onActivityDestroyed(activity: Activity) = Unit
  override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
  override fun onActivityStopped(activity: Activity) = Unit
  override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) = Unit
  override fun onActivityResumed(activity: Activity) = doOnResume()
}
