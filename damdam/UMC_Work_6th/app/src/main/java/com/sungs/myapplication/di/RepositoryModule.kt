package com.sungs.myapplication.di

import com.sungs.myapplication.data.repository.ProductLocalRepositoryImpl
import com.sungs.myapplication.data.repository.UserRemoteRepositoryImpl
import com.sungs.myapplication.domain.repository.ProductLocalRepository
import com.sungs.myapplication.domain.repository.UserRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRemoteRepository(
        impl: UserRemoteRepositoryImpl
    ): UserRemoteRepository

    @Binds
    @Singleton
    abstract fun bindProductLocalRepository(
        impl: ProductLocalRepositoryImpl
    ): ProductLocalRepository
}
