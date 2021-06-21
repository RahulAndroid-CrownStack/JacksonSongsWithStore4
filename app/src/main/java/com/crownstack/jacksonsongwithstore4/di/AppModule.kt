package com.crownstack.jacksonsongwithstore4.di

import android.content.Context
import androidx.room.Room
import coil.ImageLoader
import coil.util.DebugLogger
import com.crownstack.jacksonsongwithstore4.BuildConfig
import com.crownstack.jacksonsongwithstore4.R
import com.crownstack.jacksonsongwithstore4.network.SongsApi
import com.crownstack.jacksonsongwithstore4.roomdb.SongsDB
import com.crownstack.jacksonsongwithstore4.ui.fragments.SongsAdapter
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor(
                object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        print("[URL CREATED ]: ${chain.request().url}\n")
                        return chain.proceed(chain.request())
                    }
                })
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setDateFormat("yyyy-MM-dd")
                        .create()
                )
            )
            .build()

    @Singleton
    @Provides
    fun provideSongsApi(retrofit: Retrofit): SongsApi =
        retrofit.create(SongsApi::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SongsDB =
        Room.databaseBuilder(context.applicationContext, SongsDB::class.java, "songs_db")
            .build()


    @Singleton
    @Provides
    fun providesImageLoader(@ApplicationContext context: Context): ImageLoader =
        ImageLoader.Builder(context)
            .availableMemoryPercentage(1.0)
            .crossfade(true)
            .crossfade(2000)
            .error(R.drawable.no_image)
            .logger(DebugLogger())
            .build()


    @Provides
    fun provideSongsAdapter(imageLoader: ImageLoader) = SongsAdapter(imageLoader)
}