package me.emmarivera.weather.data.remote

import android.annotation.SuppressLint
import me.emmarivera.weather.internal.logging.Loggable
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * A kotlin singleton object which intercepts all okhttp3 requests and logs them to [Timber].
 */
object LoggingInterceptor : Interceptor, Loggable {
  override val logTag: String = "network"

  @SuppressLint("DefaultLocale")
  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val response = chain.proceed(request)

    logger.v(
      String.format(
        "%s\t%s\t\t%d",
        request.method,
        request.url,
        response.code
      )
    )

    return response
  }
}