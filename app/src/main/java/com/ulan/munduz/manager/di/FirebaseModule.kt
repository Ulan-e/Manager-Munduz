package com.ulan.munduz.manager.di

import android.content.Context
import com.ulan.munduz.manager.data.repository.Repository
import com.ulan.munduz.manager.data.repository.RepositoryImpl
import com.ulan.munduz.manager.data.repository.Storage
import com.ulan.munduz.manager.data.repository.StorageImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class FirebaseModule {

    @Singleton
    @Provides
    fun provideRepository(context: Context): Repository{
        return RepositoryImpl(context)
    }

    @Singleton
    @Provides
    fun provideStorage(context: Context): Storage{
        return StorageImpl(context)
    }
}