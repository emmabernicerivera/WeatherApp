package me.emmarivera.weather.feature.permission

import android.os.Bundle
import androidx.core.app.ActivityCompat
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_permission_required.*
import me.emmarivera.weather.R
import me.emmarivera.weather.data.repository.Permission
import me.emmarivera.weather.internal.logging.Loggable
import javax.inject.Inject

class PermissionRequiredActivity : DaggerAppCompatActivity(), PermissionRequiredView, Loggable {
  override val logTag: String = "permission-required-activity"

  companion object {
    private const val REQUEST_PERMISSION_CODE = 1212
  }

  @Inject lateinit var presenter: PermissionRequiredPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_permission_required)

    location_btn.setOnClickListener { presenter.onGrantLocationClicked() }
    presenter.onCreate()
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.onDestroy()
  }

  override fun showLocationState(permission: Permission) {
    location_btn.isEnabled = !permission.granted
    location_btn.text = when {
      permission.granted -> getString(R.string.granted)
      else -> getString(R.string.grant)
    }
  }

  override fun requestPermission(id: String) {
    logger.i("requesting permission: $id")
    ActivityCompat.requestPermissions(this, arrayOf(id), REQUEST_PERMISSION_CODE)
  }

  override fun close() {
    logger.i("close()")
    supportFinishAfterTransition()
  }
}
