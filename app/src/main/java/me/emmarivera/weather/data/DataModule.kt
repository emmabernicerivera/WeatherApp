package me.emmarivera.weather.data

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import me.emmarivera.weather.BuildConfig
import me.emmarivera.weather.data.remote.LoggingInterceptor
import me.emmarivera.weather.internal.AppScope
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Tells Dagger2 how to create our data-layer objects
 */
@Module
class DataModule {

  /**
   * Gson is used for serializing/deserializing json
   */
  @AppScope
  @Provides
  fun provideGson(): Gson = Gson()

  /**
   * Our okhttp client which retrofit uses under-the-hood to make network calls.
   */
  @AppScope
  @Provides
  fun providesOkhttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder().apply {
    retryOnConnectionFailure(true)

    if (BuildConfig.DEBUG) addInterceptor(LoggingInterceptor)
  }

  /**
   * Creates our retrofit2 instance which we will use to turn our REST API interfaces into actual
   * usable classes that can make network calls.
   */
  @AppScope
  @Provides
  fun provideRetrofitBuilder(
    gson: Gson,
    client: OkHttpClient
  ): Retrofit.Builder = Retrofit.Builder().apply {
    client(client)
    addConverterFactory(GsonConverterFactory.create(gson))
    addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
    validateEagerly(BuildConfig.DEBUG)
  }
}