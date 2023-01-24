package com.hamy.compose.unitTesting.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hamy.compose.unitTesting.data.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingItemDatabase : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao
}