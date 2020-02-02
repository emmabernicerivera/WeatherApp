package me.emmarivera.weather.feature.home.presenter

import me.emmarivera.weather.feature.home.view.HomeView
import me.emmarivera.weather.internal.ActivityScope
import me.emmarivera.weather.internal.logging.Loggable
import javax.inject.Inject

@ActivityScope
class HomePresenterImpl @Inject constructor(
    private val view: HomeView
) : HomePresenter, Loggable{

    override val logTag: String = "home-presenter"
}