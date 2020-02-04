package me.emmarivera.weather.feature.permission

import android.Manifest
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import me.emmarivera.weather.data.repository.PermissionRepository
import me.emmarivera.weather.internal.ActivityScope
import me.emmarivera.weather.internal.logging.Loggable
import javax.inject.Inject

interface PermissionRequiredPresenter {

  /**
   * To be called when the view has been created
   */
  fun onCreate()

  /**
   * To be called when the view has been destroyed
   */
  fun onDestroy()

  /**
   * To be called when the user clicks to grant the coarse location permission
   */
  fun onGrantLocationClicked()
}

@ActivityScope
class PermissionRequiredPresenterImpl @Inject constructor(
  private val view: PermissionRequiredView,
  private val repository: PermissionRepository
) : PermissionRequiredPresenter, Loggable {
  override val logTag: String = "permission-required-presenter"

  private val disposables = CompositeDisposable()

  override fun onCreate() {
    disposables.add(
      repository.location.subscribeBy(
        onNext = {
          logger.i("Updating permission state: $it")
          view.showLocationState(it)
        },
        onComplete = {
          logger.i("Completing location disposable")
        }
      )
    )

    disposables.add(
      repository.hasAllPermissions.subscribeBy(
        onNext = { hasAllPermissions ->
          if (hasAllPermissions) view.close()
        }
      )
    )
  }

  override fun onDestroy() {
    disposables.clear()
  }

  override fun onGrantLocationClicked() {
    logger.i("onGrantLocationClicked()")
    view.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION)
  }
}