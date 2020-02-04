package me.emmarivera.weather.feature.home.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import me.emmarivera.weather.R.layout
import me.emmarivera.weather.feature.home.presenter.HomePresenter
import me.emmarivera.weather.internal.logging.Loggable
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity(), HomeView,
  Loggable{
  override val logTag: String = "home-activity"

  @Inject
  lateinit var presenter: HomePresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_home)

    submit_button.setOnClickListener {presenter.searchWeatherByLocation()}
    et_latitude.addTextChangedListener( object : TextWatcher {
      override fun afterTextChanged(p0: Editable?) {}

      override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        presenter.updateLatitude(s.toString())
      }
    })

    et_longitude.addTextChangedListener( object : TextWatcher {
      override fun afterTextChanged(p0: Editable?) {
      }

      override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        presenter.updateLongitude(s.toString())
      }
    })
  }



}
