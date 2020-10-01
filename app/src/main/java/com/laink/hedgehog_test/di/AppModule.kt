package com.laink.hedgehog_test.di

import com.laink.hedgehog_test.api.JokesApi
import com.laink.hedgehog_test.repositories.JokeRepository
import com.laink.hedgehog_test.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit() =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(JokesApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(retrofitApi: JokesApi) = JokeRepository(retrofitApi)
}