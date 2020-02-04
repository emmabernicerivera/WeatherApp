package me.emmarivera.weather.feature.permission

import me.emmarivera.weather.data.repository.Permission

interface PermissionRequiredView {

  /**
   * Displays the state for the coarse location permission
   */
  fun showLocationState(permission: Permission)

  /**
   * Requests the permission to be granted
   */
  fun requestPermission(id: String)

  /**
   * Closes the current view.
   */
  fun close()
}