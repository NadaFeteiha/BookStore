package com.nadafeteih.bookstore.di

import com.nadafeteih.bookstore.data.repository.BookRepository
import com.nadafeteih.bookstore.data.repository.BookRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindBookRepository(
        bookRepositoryImp: BookRepositoryImp
    ): BookRepository

}