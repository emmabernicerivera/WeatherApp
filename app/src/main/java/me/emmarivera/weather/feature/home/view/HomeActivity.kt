package me.emmarivera.weather.feature.home.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import dagger.android.support.DaggerAppCompatActivity
import me.emmarivera.weather.R.layout
import me.emmarivera.weather.feature.home.presenter.HomePresenter
import me.emmarivera.weather.internal.logging.Loggable
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity(), HomeView,
  Loggable{

  private lateinit var mSearchButton: Button
  private lateinit var mEditTextLongitude: EditText
  private lateinit var mEditTextLatitiude: EditText

  override val logTag: String = "home-activity"

  @Inject
  lateinit var presenter: HomePresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_home)


  }
}
