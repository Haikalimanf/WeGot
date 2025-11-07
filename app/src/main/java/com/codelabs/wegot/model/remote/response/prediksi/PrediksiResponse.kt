package com.codelabs.wegot.model.remote.response.prediksi

import com.google.gson.annotations.SerializedName

data class PrediksiResponse(

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("carts")
	val carts: List<CartsItem>,

	@field:SerializedName("limit")
	val limit: Int,

	@field:SerializedName("skip")
	val skip: Int
)

data class ProductsItem(

	@field:SerializedName("discountPercentage")
	val discountPercentage: Any,

	@field:SerializedName("discountedTotal")
	val discountedTotal: Any,

	@field:SerializedName("total")
	val total: Any,

	@field:SerializedName("thumbnail")
	val thumbnail: String,

	@field:SerializedName("quantity")
	val quantity: Int,

	@field:SerializedName("price")
	val price: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String
)

data class CartsItem(

	@field:SerializedName("discountedTotal")
	val discountedTotal: Any,

	@field:SerializedName("total")
	val total: Any,

	@field:SerializedName("totalQuantity")
	val totalQuantity: Int,

	@field:SerializedName("totalProducts")
	val totalProducts: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("products")
	val products: List<ProductsItem>
)
