package me.emmarivera.weather.feature.splash.view

import android.content.Intent
import android.os.Bundle
import androidx.core.app.TaskStackBuilder
import dagger.android.support.DaggerAppCompatActivity
import me.emmarivera.weather.feature.HomeActivity
import me.emmarivera.weather.R
import me.emmarivera.weather.feature.permission.PermissionRequiredActivity
import me.emmarivera.weather.internal.logging.Loggable
import me.emmarivera.weather.feature.splash.presenter.SplashPresenter
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity(), SplashView, Loggable {
  override val logTag: String = "splash-activity"

  @Inject lateinit var presenter: SplashPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
  }

  override fun onStart() {
    super.onStart()
    presenter.onStart()
  }

  override fun onStop() {
    super.onStop()
    presenter.onStop()
  }

  override fun navigateToHome() {
    logger.i("navigateToHome()")
    startActivity(Intent(this, HomeActivity::class.java))
    supportFinishAfterTransition()
  }

  override fun navigateToPermissionRequest() {
    logger.i("navigateToPermissionRequest()")

    TaskStackBuilder.create(this)
      .addNextIntent(Intent(this, HomeActivity::class.java))
      .addNextIntentWithParentStack(Intent(this, PermissionRequiredActivity::class.java))
      .startActivities()

    supportFinishAfterTransition()
  }
}
