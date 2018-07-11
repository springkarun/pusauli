package com.digital_pusauli.view.login_upload.RegisterShop

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface APIService {
    /*@Multipart
    @POST("/upload_multi_files/MultiUpload.php")
    Call<ResponseBody> uploadFile(@Part MultipartBody.Part file, @Part("name") RequestBody name);*/

    @Multipart
    @POST("/uploaded_image.php")
    fun uploadSingleFile(
          //  @Header("accessToken") accessToken: String,
            @Part file: MultipartBody.Part,
            @Part("name") name: RequestBody,
            @Part("age") age: RequestBody,
            @Part("mobile") mobile: RequestBody): Call<Model>

    @Multipart
    //@POST("/upload_multi_files/MultiUpload.php")
    @POST("/upload_multi_files/MultiPartUpload.php")
    fun uploadMultiFile(@Part file1: MultipartBody.Part, @Part file2: MultipartBody.Part, @Part file3: MultipartBody.Part): Call<Model>

    //@Multipart
    //@FormUrlEncoded
    //@POST("/upload_multi_files/MultiUpload.php")
    @POST("/ride")
    fun uploadMultiFile(
            @Header("access_token") accessToken: String,
            @Body file: RequestBody): Call<ResponseBody>

    @FormUrlEncoded
    @PUT("/api/register")
    fun getStatus(
            @Field("username") username: String,
            @Field("email") email: String,
            @Field("password") password: String,
            @Field("fbID") fbID: String,
            @Field("gmailID") gmailID: String,
            @Field("twitID") twitID: String,
            @Field("gender") gender: String,
            @Field("birthDate") birthDate: String,
            @Field("location") location: String,
            @Field("longitude") longitude: String,
            @Field("latitude") latitude: String,
            @Field("profileImage") profileImage: String): Call<ResponseBody>
    //@Field parameters can only be used with form encoding. (parameter #2)
}
