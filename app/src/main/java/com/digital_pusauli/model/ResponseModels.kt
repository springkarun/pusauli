package com.digital_pusauli.model
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName




data class ResponseModels(
    @SerializedName("status") val status: Boolean? = false,
    @SerializedName("message") val message: String? = "",
    @SerializedName("data") val data: List<Data?>? = listOf()
)

data class Data(
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("categoryId") var categoryId: String? = "",
        @SerializedName("categoryName") var categoryName: String?="",
        @SerializedName("shopId") val shopId: String? = "",
        @SerializedName("shopName") val shopName: String? = "",
        @SerializedName("shopReg") val shopReg: String? = "",
        @SerializedName("shopAvatar") val shopAvatar: String? = "",
        @SerializedName("shopEmail") val shopEmail: String? = "",
        @SerializedName("shopMobile") val shopMobile: String? = "",
        @SerializedName("shopAddress") val shopAddress: String? = "",
        @SerializedName("shopNearst") val shopNearst: String? = "",
        @SerializedName("shopTime") val shopTime: String? = "",
        @SerializedName("shopRating") val shopRating: String? = "",
        @SerializedName("shopLatitude") val shopLatitude: String? = "",
        @SerializedName("shopLongitude") val shopLongitude: String? = "",
        @SerializedName("ownerName") val ownerName: String? = "",
        @SerializedName("ownerEmail") val ownerEmail: String? = "",
        @SerializedName("ownerAvatar") val ownerAvatar: String? = "",
        @SerializedName("ownerContact") val ownerContact: String? = "",
        @SerializedName("colorCode") val colorCode: String? = "",
        @SerializedName("reg_timeStamp") val regTimeStamp: String? = ""
):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(id)
        dest.writeString(categoryId)
        dest.writeString(categoryName)
        dest.writeString(shopId)
        dest.writeString(shopName)
        dest.writeString(shopReg)
        dest.writeString(shopAvatar)
        dest.writeString(shopEmail)
        dest.writeString(shopMobile)
        dest.writeString(shopAddress)
        dest.writeString(shopNearst)
        dest.writeString(shopTime)
        dest.writeString(shopRating)
        dest.writeString(shopLatitude)
        dest.writeString(shopLongitude)
        dest.writeString(ownerName)
        dest.writeString(ownerEmail)
        dest.writeString(ownerAvatar)
        dest.writeString(ownerContact)
        dest.writeString(colorCode)
        dest.writeString(regTimeStamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }

        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }

}