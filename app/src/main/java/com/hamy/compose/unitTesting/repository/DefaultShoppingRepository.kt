package com.hamy.compose.unitTesting.repository

import androidx.lifecycle.LiveData
import com.hamy.compose.unitTesting.data.ShoppingItem
import com.hamy.compose.unitTesting.data.local.ShoppingDao
import com.hamy.compose.unitTesting.data.remote.ImageResponse
import com.hamy.compose.unitTesting.data.remote.PixBayApi
import com.hamy.compose.unitTesting.utils.Resource
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val dao: ShoppingDao,
    private val pixBayApi: PixBayApi,
) : ShoppingRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        dao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        dao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItem(): LiveData<List<ShoppingItem>> {
        return dao.observeAllShoppingItems()
    }

    override fun getTotalPrice(): LiveData<Float> {
        return dao.observeTotalPrice()
    }

    override suspend fun searchForImage(query: String): Resource<ImageResponse> {
        return try {
            val response = pixBayApi.searchForImage(query)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Unknown Error", null)
            } else {
                Resource.error("Unknown Error", null)
            }

        } catch (e: Exception) {
            Resource.error("Internet issue", null)
        }
    }

}