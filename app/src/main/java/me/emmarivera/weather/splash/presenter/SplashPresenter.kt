package me.emmarivera.weather.splash.presenter

interface SplashPresenter {

  /**
   * To be called when the view has been started
   */
  fun onStart()

  /**
   * To be called when the view has been stopped
   */
  fun onStop()
}