package com.digital_pusauli.model
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName



data class ResponseModel(
    @SerializedName("status") val status: Boolean,
    @SerializedName("result") val result: List<Result>
)

data class Result(
    @SerializedName("id") val id: String,
    @SerializedName("shopId") val shopId: String,
    @SerializedName("shopName") val shopName: String,
    @SerializedName("shopReg") val shopReg: String,
    @SerializedName("shopAvatar") val shopAvatar: String,
    @SerializedName("shopEmail") val shopEmail: String,
    @SerializedName("shopMobile") val shopMobile: String,
    @SerializedName("ifscCode") val ifscCode: String,
    @SerializedName("shopAddress") val shopAddress: String,
    @SerializedName("shopNearst") val shopNearst: String,
    @SerializedName("shopTime") val shopTime: String,
    @SerializedName("shopRating") val shopRating: String,
    @SerializedName("shopLatitude") val shopLatitude: String,
    @SerializedName("shopLongitude") val shopLongitude: String,
    @SerializedName("ownerName") val ownerName: String,
    @SerializedName("ownerEmail") val ownerEmail: String,
    @SerializedName("ownerContact") val ownerContact: String,
    @SerializedName("ownerAvatar") val ownerAvatar: String,
    @SerializedName("reg_date") val regDate: String,
    @SerializedName("shopCode") val shopCode: String,
    @SerializedName("colorCode") val colorCode: String
):Parcelable {
    constructor(parcel: Parcel) : this(
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
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(shopId)
        dest.writeString(shopName)
        dest.writeString(shopReg)
        dest.writeString(shopAvatar)
        dest.writeString(shopEmail)
        dest.writeString(shopMobile)
        dest.writeString(ifscCode)
        dest.writeString(shopAddress)
        dest.writeString(shopNearst)
        dest.writeString(shopTime)
        dest.writeString(shopRating)
        dest.writeString(shopLatitude)
        dest.writeString(shopLongitude)
        dest.writeString(ownerName)
        dest.writeString(ownerEmail)
        dest.writeString(ownerContact)
        dest.writeString(ownerAvatar)
        dest.writeString(regDate)
        dest.writeString(shopCode)
        dest.writeString(colorCode)
    }

    override fun describeContents(): Int {
       return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}