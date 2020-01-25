package me.emmarivera.weather.splash.view

import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import me.emmarivera.weather.MainActivity
import me.emmarivera.weather.R
import me.emmarivera.weather.internal.Loggable
import me.emmarivera.weather.splash.presenter.SplashPresenter
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

    startActivity(
      Intent(this, MainActivity::class.java)
    )
    supportFinishAfterTransition()
  }
}
