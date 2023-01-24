package com.hamy.compose.unitTesting.utils

import android.content.Context
import androidx.room.Room
import com.hamy.compose.unitTesting.data.local.ShoppingDao
import com.hamy.compose.unitTesting.data.local.ShoppingItemDatabase
import com.hamy.compose.unitTesting.data.remote.PixBayApi
import com.hamy.compose.unitTesting.repository.DefaultShoppingRepository
import com.hamy.compose.unitTesting.repository.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ShoppingApplication::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingDataBase(  @ApplicationContext context : Context) = Room.databaseBuilder(context,
        ShoppingItemDatabase::class.java, DATABASE_NAME
    )

    @Singleton
    @Provides
    fun provideShoppingDao(database: ShoppingItemDatabase) = database.shoppingDao()

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(dao: ShoppingDao,api: PixBayApi) = DefaultShoppingRepository(dao,api) as ShoppingRepository

    @Singleton
    @Provides
    fun providePixelBayApi() : PixBayApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixBayApi::class.java)

    }
}