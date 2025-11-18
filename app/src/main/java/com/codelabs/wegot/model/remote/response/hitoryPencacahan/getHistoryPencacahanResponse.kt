package com.codelabs.wegot.model.remote.response.hitoryPencacahan

data class GetHistoryPencacahanResponse(
	val data: List<DataItem>,
	val status: String
)

data class DataItem(
	val tanggalWaktu: String,
	val createdAt: String,
	val totalSampah: Any,
	val catatan: String,
	val id: Int,
	val userId: Int
)

