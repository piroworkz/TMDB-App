package com.piroworkz.bluelabstmdbapp.app.di

import com.piroworkz.bluelabstmdbapp.app.data.local.datasource.RoomLocalDataSource
import com.piroworkz.bluelabstmdbapp.app.data.server.MoviesServerDataSource
import com.piroworkz.bluelabstmdbapp.data.datasource.LocalDataSource
import com.piroworkz.bluelabstmdbapp.data.datasource.MoviesRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun provideRoomLocalDataSource(roomLocalDataSource: RoomLocalDataSource): LocalDataSource

    @Binds
    abstract fun provideRemoteMoviesDataSource(moviesServerDataSource: MoviesServerDataSource): MoviesRemoteDataSource
}