package com.ahmadmaaz1.newsy.di

import android.app.Application
import com.ahmadmaaz1.newsy.data.manager.LocalUserManagerIml
import com.ahmadmaaz1.newsy.domain.manager.LocalUserManager
import com.ahmadmaaz1.newsy.domain.usecause.appEntry.AppEntryUseCause
import com.ahmadmaaz1.newsy.domain.usecause.appEntry.ReadAppEntry
import com.ahmadmaaz1.newsy.domain.usecause.appEntry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(context: Application): LocalUserManager{
        return LocalUserManagerIml(context)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCause(localUserManager: LocalUserManager): AppEntryUseCause{

        return AppEntryUseCause(readAppEntry = ReadAppEntry(localUserManager), appSaveAppEntry = SaveAppEntry(localUserManager))
    }

}