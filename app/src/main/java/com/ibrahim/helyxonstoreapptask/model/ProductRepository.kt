package com.ibrahim.helyxonstoreapptask.model

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ibrahim.helyxonstoreapptask.api.RetrofitClient
import retrofit2.HttpException
import java.io.IOException

class ProductRepository(private val context: Context) {

    private val _productList = MutableLiveData<ArrayList<ProductData>>()
    val productList: LiveData<ArrayList<ProductData>> = _productList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    // To fetch products from server
    suspend fun getProductList() {
        if (isNetworkAvailable()) {
            try {
                val response = RetrofitClient.apiService.getProducts()

                if (response.isSuccessful) {
                    response.body()?.let {
                            _productList.postValue(it)
                    } ?: run {
                        _errorMessage.postValue("No products found.")
                    }
                } else {
                    _errorMessage.postValue("Error: ${response.message()}")
                }

            } catch (e: HttpException) {
                _errorMessage.postValue("HTTP error: ${e.message()}")
            } catch (e: IOException) {
                _errorMessage.postValue("Network error: ${e.localizedMessage}")
            } catch (e: Exception) {
                _errorMessage.postValue("Unexpected error: ${e.localizedMessage}")
            }
        } else {
            _errorMessage.postValue("No internet connection.")
        }
    }

    // To check network availability
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
