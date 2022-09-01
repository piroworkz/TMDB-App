package com.piroworkz.bluelabstmdbapp.app.di

import android.app.Application
import androidx.room.Room
import com.piroworkz.bluelabstmdbapp.R
import com.piroworkz.bluelabstmdbapp.app.data.local.AppDatabase
import com.piroworkz.bluelabstmdbapp.app.data.local.MovieDao
import com.piroworkz.bluelabstmdbapp.app.data.server.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    @ApiKey
    fun provideApiKey(application: Application): String =
        application.getString(R.string.api_key)

    @Singleton
    @Provides
    @BaseUrl
    fun provideBaseUrl(application: Application): String =
        application.getString(R.string.base_url)

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase = Room.databaseBuilder(
        application, AppDatabase::class.java, "app_database"
    ).fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideMovieDao(database: AppDatabase): MovieDao = database.movieDao


    @Singleton
    @Provides
    fun provideOkhttp3Client(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Singleton
    @Provides
    fun provideMovieServer(client: OkHttpClient, @BaseUrl baseUrl: String): MoviesService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
}