package com.hamy.compose.unitTesting.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamy.compose.unitTesting.data.ShoppingItem
import com.hamy.compose.unitTesting.data.remote.ImageResponse
import com.hamy.compose.unitTesting.repository.ShoppingRepository
import com.hamy.compose.unitTesting.utils.Event
import com.hamy.compose.unitTesting.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.http.Query

@HiltViewModel // create seperate viewModel for each view. for learning purpose creating single*
class ShoppingViewModel constructor(
    private val shoppingRepository: ShoppingRepository,
) : ViewModel() {

    val shoppingItem = shoppingRepository.observeAllShoppingItem()
    val totalPrice = shoppingRepository.getTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> = _images

    private val _currentImageUrl = MutableLiveData<String>()
    val currentImageUrl: LiveData<String> = _currentImageUrl

    private val _insertShoppingItem = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItem: LiveData<Event<Resource<ShoppingItem>>> = _insertShoppingItem


    fun setImageUrl(url: String){
        _currentImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        shoppingRepository.deleteShoppingItem(shoppingItem)
    }

    fun searchShoppingItem(query: String) = viewModelScope.launch {
        shoppingRepository.searchForImage(query)
    }

    fun insertShoppingItemInDB(shoppingItem: ShoppingItem) = viewModelScope.launch {
        shoppingRepository.insertShoppingItem(shoppingItem)
    }
}