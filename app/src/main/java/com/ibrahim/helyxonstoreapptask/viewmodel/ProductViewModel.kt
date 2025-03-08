package com.ibrahim.helyxonstoreapptask.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahim.helyxonstoreapptask.model.ProductData
import com.ibrahim.helyxonstoreapptask.model.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val context: Context) : ViewModel() {

    private val productRepository = ProductRepository(context)

    val productList: LiveData<ArrayList<ProductData>> get() = productRepository.productList

    val errorMessage: LiveData<String> get() = productRepository.errorMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    // To fetch product list from the repository
    fun fetchProductList() {
        _loading.postValue(true)
        viewModelScope.launch {
            productRepository.getProductList()
            _loading.postValue(false)
        }
    }

}
