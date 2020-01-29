package com.tidaltestapp.di.network

import android.app.Application
import android.content.Context
import com.tidaltestapp.data.interactor.Interactor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by naderbaltaji on 2020-01-29
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesContext(app: Application): Context = app

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder().baseUrl("https://api.deezer.com/")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkService(retrofit: Retrofit) : NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideInteractor(
        networkService: NetworkService
    ): Interactor {
        return Interactor(networkService)
    }
}