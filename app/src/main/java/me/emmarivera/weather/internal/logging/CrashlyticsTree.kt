package me.emmarivera.weather.internal.logging

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import me.emmarivera.weather.internal.AppScope
import timber.log.Timber
import java.io.PrintWriter
import java.io.StringWriter
import javax.inject.Inject

/**
 * Used in release builds to send runtime-crashes to firebase crashlytics.
 */
@AppScope
class CrashlyticsTree @Inject constructor(
  private val crashlytics: FirebaseCrashlytics
) : Timber.Tree() {

  private fun getTag(tag: String?) = "${tag ?: "unknown"}:"

  private fun stacktraceFrom(throwable: Throwable): String {
    val stringWriter = StringWriter()
    throwable.printStackTrace(PrintWriter(stringWriter))
    return stringWriter.toString()
  }

  private fun formatNormalMessage(message: String, throwable: Throwable?): String {
    if (throwable == null) return message

    return "$message\n${stacktraceFrom(throwable)}"
  }

  override fun log(
    priority: Int,
    tag: String?,
    message: String,
    t: Throwable?
  ) {
    when (priority) {
      Log.INFO, Log.VERBOSE, Log.WARN -> crashlytics.log(
        "${getTag(tag)} ${formatNormalMessage(message, t)}"
      )
      Log.ERROR, Log.ASSERT -> {
        crashlytics.log("${getTag(tag)} $message")
        if (t != null) crashlytics.recordException(t)
      }
    }
  }
}