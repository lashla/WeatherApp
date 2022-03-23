package com.example.weatherapp.di

import com.example.weatherapp.ui.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule  {

    @Singleton
    @Provides
    fun providesRepository(): MainRepository {
        return MainRepository()
    }

}