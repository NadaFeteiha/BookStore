package com.nadafeteih.bookstore.di

import com.nadafeteih.bookstore.data.remote.RemoteDataSource
import com.nadafeteih.bookstore.data.remote.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(
        repositoryImp: RemoteDataSourceImp
    ): RemoteDataSource

}