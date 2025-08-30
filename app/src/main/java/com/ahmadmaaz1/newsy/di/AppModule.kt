package com.ahmadmaaz1.newsy.di

import android.app.Application
import com.ahmadmaaz1.newsy.data.manager.LocalUserManagerIml
import com.ahmadmaaz1.newsy.data.remot.NewsApi
import com.ahmadmaaz1.newsy.data.repositoryt.RepositoryIml
import com.ahmadmaaz1.newsy.domain.manager.LocalUserManager
import com.ahmadmaaz1.newsy.domain.repository.Repository
import com.ahmadmaaz1.newsy.domain.usecause.appEntry.AppEntryUseCause
import com.ahmadmaaz1.newsy.domain.usecause.appEntry.ReadAppEntry
import com.ahmadmaaz1.newsy.domain.usecause.appEntry.SaveAppEntry
import com.ahmadmaaz1.newsy.domain.usecause.news.GetNews
import com.ahmadmaaz1.newsy.domain.usecause.news.NewsUseCause
import com.ahmadmaaz1.newsy.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(context: Application): LocalUserManager {
        return LocalUserManagerIml(context)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCause(localUserManager: LocalUserManager): AppEntryUseCause {

        return AppEntryUseCause(
            readAppEntry = ReadAppEntry(localUserManager),
            appSaveAppEntry = SaveAppEntry(localUserManager)
        )
    }

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepo (newsApi: NewsApi): Repository{
        return RepositoryIml(newsApi = newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCause(repository: Repository): NewsUseCause{
        return NewsUseCause(getNews = GetNews(repository = repository))
    }

}