package com.codelabs.wegot.ui.prediksi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.wegot.model.remote.response.prediksi.CartsItem
import com.codelabs.wegot.repository.PrediksiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrediksiViewModel @Inject constructor(
    private val repository: PrediksiRepository
) : ViewModel() {
    private val _carts = MutableLiveData<List<CartsItem>>()
    val carts: LiveData<List<CartsItem>> = _carts

//    fun getCarts() {
//        viewModelScope.launch {
//            try {
//                val response = repository.getCarts(0, 10)
//                _carts.value = response.carts
//            } catch (e: Exception) {
//                // Handle error
//            }
//        }
//    }
}
