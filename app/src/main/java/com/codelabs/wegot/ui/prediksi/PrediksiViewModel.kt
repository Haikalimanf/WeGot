package com.codelabs.wegot.ui.prediksi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.wegot.model.remote.network.ApiResponse
import com.codelabs.wegot.model.remote.response.prediksi.GetSiklusResponse
import com.codelabs.wegot.repository.PrediksiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrediksiViewModel @Inject constructor(
    private val repository: PrediksiRepository
) : ViewModel() {
    private val _siklusData = MutableLiveData<ApiResponse<GetSiklusResponse>>()
    val siklusData: LiveData<ApiResponse<GetSiklusResponse>> = _siklusData

    fun getSiklus() {
        viewModelScope.launch {
            _siklusData.value = ApiResponse.Empty
            val result = repository.getSiklus()
            _siklusData.value = result
        }
    }
}
