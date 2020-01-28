package me.emmarivera.weather.home

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.emmarivera.weather.R.layout
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity(), HomeView {

  @Inject lateinit var presenter: HomePresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)

    button.setOnClickListener {
      presenter.onAddOneClicked()
    }
  }

  override fun setCount(count: Int) {
    count_tv.text = "Count: $count"
  }
}
