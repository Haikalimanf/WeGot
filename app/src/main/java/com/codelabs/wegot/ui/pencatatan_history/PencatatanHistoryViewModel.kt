package com.codelabs.wegot.ui.pencatatan_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PencatatanHistoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Sedang Dikembangkan"
    }
    val text: LiveData<String> = _text
}