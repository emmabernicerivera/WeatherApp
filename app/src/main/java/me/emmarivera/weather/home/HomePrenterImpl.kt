package me.emmarivera.weather.home

import me.emmarivera.weather.internal.ActivityScope
import javax.inject.Inject

@ActivityScope
class HomePrenterImpl @Inject constructor(
  private val view: HomeView
) : HomePresenter {

  private var counter: Int = 0

  override fun onAddOneClicked() {
    counter += 1
    view.setCount(counter)
  }
}