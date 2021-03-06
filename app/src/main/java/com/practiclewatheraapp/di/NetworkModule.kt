package com.practiclewatheraapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.practiclewatheraapp.BuildConfig
import com.practiclewatheraapp.base.MyApplication
import com.practiclewatheraapp.source.local.AppDatabase
import com.practiclewatheraapp.source.remote.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val READ_TIMEOUT = 30L
private const val WRITE_TIMEOUT = 30L
private const val CONNECTION_TIMEOUT = 10L
private const val CACHE_SIZE_BYTES = 10 * 1024 * 1024L

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    }

    @Singleton
    @Provides
    fun provideCache(context: Context): Cache = Cache(File(context.cacheDir.absolutePath), CACHE_SIZE_BYTES)

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MyApplication {
        return app as MyApplication
    }

    @Singleton
    @Provides
    fun provideContext(app: MyApplication): Context = app.applicationContext

    @Singleton
    @Provides
    fun provideOkHttpClient(
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(Interceptor {
                val requestBuilder = it.request().newBuilder()
                it.proceed(requestBuilder.build())
            })
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
            })
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()


    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)

    @Provides
    @Singleton
    fun provideDatabase(application: Application)
            = Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun providesUserDao(weatherDatabase: AppDatabase) =
        weatherDatabase.cityDao
}