package com.example.ffxivproject.di

import android.content.Context
import com.example.ffxivproject.data.api.db.FFXIVDao
import com.example.ffxivproject.data.api.db.FFXIVDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideFFXIVDatabase(@ApplicationContext context: Context): FFXIVDatabase{
        return FFXIVDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideFFXIVDao(database: FFXIVDatabase): FFXIVDao{
        return database.ffxivDao()
    }
}