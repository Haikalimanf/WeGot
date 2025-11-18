package com.codelabs.wegot.ui.prediksi.tambah_fase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.wegot.model.remote.network.ApiResponse
import com.codelabs.wegot.model.remote.response.prediksi.AddFaseResponse
import com.codelabs.wegot.repository.PrediksiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TambahFaseViewModel @Inject constructor(
    private val repository: PrediksiRepository
) : ViewModel() {

    private val _addFaseResult = MutableLiveData<ApiResponse<AddFaseResponse>>()
    val addFaseResult: LiveData<ApiResponse<AddFaseResponse>> = _addFaseResult

    fun addFase(
        siklusId: Int,
        jenis: String,
        tanggal: String,
        jumlahTelur: Int,
        mediaTelur: String,
        catatan: String
    ) {
        viewModelScope.launch {
            _addFaseResult.value = ApiResponse.Empty
            val result = repository.addFase(siklusId, jenis, tanggal, jumlahTelur, mediaTelur, catatan)
            _addFaseResult.value = result
        }
    }

    fun addFasePembesaranWithPanen(
        siklusId: Int,
        tanggalPembesaran: String,
        jumlahMakanan: Int,
        catatan: String
    ) {
        viewModelScope.launch {
            _addFaseResult.value = ApiResponse.Empty

            try {
                // 1. Tambah fase PEMBESARAN
                val pembesaranResult = repository.addFasePembesaran(
                    siklusId = siklusId,
                    jenis = "PEMBESARAN",
                    tanggal = tanggalPembesaran,
                    jumlahMakanan = jumlahMakanan,
                    catatan = catatan
                )

                when (pembesaranResult) {
                    is ApiResponse.Success -> {
                        // 2. Tambah fase PANEN dengan input yang sama
                        val panenResult = repository.addFasePanen(
                            siklusId = siklusId,
                            jenis = "PANEN",
                            tanggal = tanggalPembesaran, // Gunakan tanggal yang sama
                            jumlahMakanan = jumlahMakanan, // Gunakan jumlah makanan yang sama
                            catatan = "Fase panen - $catatan"
                        )

                        _addFaseResult.value = panenResult
                    }
                    is ApiResponse.Error -> {
                        _addFaseResult.value = pembesaranResult
                    }
                    is ApiResponse.Empty -> {
                        _addFaseResult.value = ApiResponse.Error("Terjadi kesalahan saat menambah fase pembesaran")
                    }
                }
            } catch (e: Exception) {
                _addFaseResult.value = ApiResponse.Error(e.message ?: "Terjadi kesalahan")
            }
        }
    }
}
