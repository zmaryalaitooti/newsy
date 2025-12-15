package com.ahmadmaaz1.newsy.di

import android.app.Application
import androidx.room.Room
import com.ahmadmaaz1.newsy.data.local.NewsDao
import com.ahmadmaaz1.newsy.data.local.NewsDb
import com.ahmadmaaz1.newsy.data.local.NewsTypeConverter
import com.ahmadmaaz1.newsy.data.manager.LocalUserManagerIml
import com.ahmadmaaz1.newsy.data.remot.NewsApi
import com.ahmadmaaz1.newsy.data.repositoryt.RepositoryIml
import com.ahmadmaaz1.newsy.domain.manager.LocalUserManager
import com.ahmadmaaz1.newsy.domain.repository.Repository
import com.ahmadmaaz1.newsy.domain.usecause.appEntry.AppEntryUseCause
import com.ahmadmaaz1.newsy.domain.usecause.appEntry.ReadAppEntry
import com.ahmadmaaz1.newsy.domain.usecause.appEntry.SaveAppEntry
import com.ahmadmaaz1.newsy.domain.usecause.news.DeleteArticle
import com.ahmadmaaz1.newsy.domain.usecause.news.GetNews
import com.ahmadmaaz1.newsy.domain.usecause.news.InsertArticle
import com.ahmadmaaz1.newsy.domain.usecause.news.NewsSearch
import com.ahmadmaaz1.newsy.domain.usecause.news.NewsUseCause
import com.ahmadmaaz1.newsy.domain.usecause.news.SelectArticleById
import com.ahmadmaaz1.newsy.domain.usecause.news.SelectArticles
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
    fun provideNewsRepo(newsApi: NewsApi,newsDao: NewsDao): Repository {
        return RepositoryIml(newsApi = newsApi,newsDao)
    }

    @Provides
    @Singleton
    fun provideNewsUseCause(repository: Repository): NewsUseCause {
        return NewsUseCause(
            getNews = GetNews(repository = repository),
            searchNews = NewsSearch(repository = repository),
            deleteArticle = DeleteArticle(repository),
            selectArticles = SelectArticles(repository),
            insertArticle = InsertArticle(repository),
            selectArticleById = SelectArticleById(repository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDb(application: Application): NewsDb {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDb::class.java,
            name = "Newsdb.db",
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(newsDb: NewsDb): NewsDao = newsDb.getNewsDao()


}

