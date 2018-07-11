package com.digital_pusauli.view.login_upload.RegisterShop
import com.google.gson.annotations.SerializedName



data class Model(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Data>
)

data class Data(
    @SerializedName("id") val id: String,
    @SerializedName("pathToFile") val pathToFile: String,
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: String,
    @SerializedName("mobile") val mobile: String
)