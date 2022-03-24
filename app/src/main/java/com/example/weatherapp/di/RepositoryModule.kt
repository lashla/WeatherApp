package com.example.weatherapp.di

import com.example.weatherapp.ui.MainRepository
import com.example.weatherapp.ui.RepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule  {

    @Provides
    @Singleton
    fun providesRepository() : RepositoryInterface {
        return MainRepository()
    }

}