package me.emmarivera.weather.feature.splash.presenter

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import me.emmarivera.weather.data.repository.PermissionRepository
import me.emmarivera.weather.internal.ActivityScope
import me.emmarivera.weather.internal.logging.Loggable
import me.emmarivera.weather.feature.splash.view.SplashView
import java.security.AllPermission
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ActivityScope
class SplashPresenterImpl @Inject constructor(
  private val view: SplashView,
  private val permissionRepository: PermissionRepository
) : SplashPresenter, Loggable {

  override val logTag: String = "splash-presenter"

  companion object {
    private const val splashDelay = 2_000L
  }

  private var disposable: Disposable? = null

  override fun onStart() {
    disposable = Observable.timer(splashDelay, TimeUnit.MILLISECONDS)
      .observeOn(AndroidSchedulers.mainThread())
      .flatMapSingle { permissionRepository.hasAllPermissions.firstOrError() }
      .subscribeBy(
        onNext = ::onStartupDelayComplete,
        onError = { throwable ->
          logger.e(throwable, "An unknown error occurred when delaying the startup transition.")
        }
      )
  }

  private fun onStartupDelayComplete(hasAllPermissions: Boolean) = when {
    hasAllPermissions -> {
      logger.i("Navigating to home screen")
      view.navigateToHome()
    }
    else -> {
      logger.i("Navigating to permission required activity")
      view.navigateToPermissionRequest()
    }
  }

  override fun onStop() {
    // If the user backgrounds the app before 2 seconds is up
    // we want to cancel the transition, and run it again when they foreground the app
    if (disposable?.isDisposed == false) {
      logger.i("Cancelling transition after delay.")
      disposable?.dispose()
    }
  }
}