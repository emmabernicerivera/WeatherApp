package me.emmarivera.weather.feature

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import me.emmarivera.weather.R.layout

class HomeActivity : DaggerAppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_home)
  }
}
