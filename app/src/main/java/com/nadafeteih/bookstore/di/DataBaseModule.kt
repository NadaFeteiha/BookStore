package com.nadafeteih.bookstore.di

import android.content.Context
import com.nadafeteih.bookstore.bookdb
import com.nadafeteih.bookstore.data.local.BookDataSource
import com.nadafeteih.bookstore.data.local.BookDataSourceImp
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


class DriverFactory(private val context: Context) {

    fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(bookdb.Schema, context, "bookdb.db")
    }
}

class AndroidPartyDatabaseFactory(private val driverFactory: DriverFactory) {

    fun createDriver(): bookdb {
        return bookdb(
            driver = driverFactory.createDriver()
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): bookdb {
        return AndroidPartyDatabaseFactory(
            driverFactory = DriverFactory(context = context)
        ).createDriver()
    }

    @Provides
    @Singleton
    fun provideAndroidPartyCache(database: bookdb): BookDataSource {
        return BookDataSourceImp(bookDataBase = database)
    }
}