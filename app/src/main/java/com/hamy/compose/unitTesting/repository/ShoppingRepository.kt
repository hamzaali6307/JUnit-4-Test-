package com.hamy.compose.unitTesting.repository

import androidx.lifecycle.LiveData
import com.hamy.compose.unitTesting.data.ShoppingItem
import com.hamy.compose.unitTesting.data.remote.ImageResponse
import com.hamy.compose.unitTesting.utils.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItem(): LiveData<List<ShoppingItem>>

    fun getTotalPrice(): LiveData<Float>

    suspend fun searchForImage(query:String) : Resource<ImageResponse>
}