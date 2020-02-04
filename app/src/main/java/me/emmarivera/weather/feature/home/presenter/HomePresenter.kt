package me.emmarivera.weather.feature.home.presenter

interface HomePresenter {

    /**
     * To be called when the user clicks submit after entering
     * longitude and latitude.
     */
    fun searchWeatherByLocation()

    /**
     * To be called when the user enters latitude
     */
    fun updateLatitude(toString: String)

    /**
     * To be called when the user enters longitude
     */
    fun updateLongitude(toString: String)
}