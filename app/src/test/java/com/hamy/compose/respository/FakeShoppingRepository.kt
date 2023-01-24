package com.hamy.compose.respository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hamy.compose.unitTesting.data.ShoppingItem
import com.hamy.compose.unitTesting.data.remote.ImageResponse
import com.hamy.compose.unitTesting.repository.ShoppingRepository
import com.hamy.compose.unitTesting.utils.Resource

class FakeShoppingRepository : ShoppingRepository {
    private val shoppingItems = mutableListOf<ShoppingItem>()
    private val observeAbleShoppingItem = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observeTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    private fun setNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observeAbleShoppingItem.postValue(shoppingItems)
        observeTotalPrice.postValue(getPrice())
    }

    private fun getPrice(): Float {
        return shoppingItems.sumByDouble { it.price.toDouble() }.toFloat()
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override fun observeAllShoppingItem(): LiveData<List<ShoppingItem>> {
        return observeAbleShoppingItem
    }

    override fun getTotalPrice(): LiveData<Float> {
        return observeTotalPrice
    }

    override suspend fun searchForImage(query: String): Resource<ImageResponse> {
       return if(shouldReturnNetworkError){
           Resource.error("Network Error",null)
       }else{
           Resource.success(ImageResponse(listOf(),0,0))
       }
    }
}