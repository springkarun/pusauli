package com.digital_pusauli.model
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName




data class ResponseModels(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Data>
)

data class Data(

        @SerializedName("id") val id: Int?=null,
        @SerializedName("categoryId") var categoryId: String?=null,
        @SerializedName("categoryName") var categoryName: String?=null,

        @SerializedName("shopId") var shopId: String?=null


)