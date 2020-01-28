package me.emmarivera.weather.internal.logging

import timber.log.Timber

interface Loggable {
  val logTag: String
  val logger: Timber.Tree get() = Timber.tag(logTag)
}