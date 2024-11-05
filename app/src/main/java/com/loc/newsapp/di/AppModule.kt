package com.loc.newsapp.di

import android.app.Application
import androidx.room.Room
import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.data.local.NewsDatabase
import com.loc.newsapp.data.local.NewsTypeConverter
import com.loc.newsapp.data.manager.LocalUserManagerImpl
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.data.repository.NewsRepositoryImpl
import com.loc.newsapp.domain.manager.LocalUserManager
import com.loc.newsapp.domain.repository.NewsRepository
import com.loc.newsapp.domain.usecases.app_entry.AppEntryUseCase
import com.loc.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.loc.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.loc.newsapp.domain.usecases.news.DeleteArticleUseCase
import com.loc.newsapp.domain.usecases.news.GetArticlesUseCase
import com.loc.newsapp.domain.usecases.news.GetNewUserCase
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import com.loc.newsapp.domain.usecases.news.SearchNewsUseCase
import com.loc.newsapp.domain.usecases.news.UpsertArticleUseCase
import com.loc.newsapp.utils.Constants.BASE_URL
import com.loc.newsapp.utils.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntry(localUserManager: LocalUserManager): AppEntryUseCase =
        AppEntryUseCase(ReadAppEntry(localUserManager), SaveAppEntry(localUserManager))

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUserCases(newsRepository: NewsRepository, newsDao: NewsDao): NewsUseCases =
        NewsUseCases(
            getNewsUseCase = GetNewUserCase(newsRepository),
            searchNewsUseCase = SearchNewsUseCase(newsRepository),
            upsertArticleUseCase = UpsertArticleUseCase(newsDao),
            deleteArticleUseCase = DeleteArticleUseCase(newsDao),
            getArticlesUseCase = GetArticlesUseCase(newsDao)
        )

    @Provides
    @Singleton
    fun provideNewsDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(
            context = application, klass = NewsDatabase::class.java, name = NEWS_DATABASE_NAME
        ).addTypeConverter(
            NewsTypeConverter()
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.newsDao
}