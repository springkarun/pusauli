package com.digital_pusauli.restservices
import com.digital_pusauli.model.ResponseModel
import com.digital_pusauli.view.login_upload.RegisterShop.Model
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface APIService {


   /* @GET("android/jsonarray/")
    fun register(): Observable<List<Android>>
*/



    @POST("/categrory_details.php")
    @FormUrlEncoded
    fun getCategrory(@Field("category_id")
                     catg_name: String):
            Observable<ResponseModel>


    @Multipart
    @POST("/uploaded_image.php")
    fun uploadPhoto(@Part("name")  name: RequestBody,
                    @Part("age") age: RequestBody,
                    @Part("mobile") mobile: RequestBody,
                    @Part file: MultipartBody.Part): Observable<Model>

/*

    @Multipart
    @POST("/immigration/api/updateProfile")
    fun postImage(
                  @Part image: MultipartBody.Part,
                  @Part("firstName") firstName: String,
                  @Part("lastName") lastName: String,
                  @Part("contact") contact: String,
                  @Part("countryCode") countryCode: String
    ): Call<ResponseModel>
*/





    /*@Headers("Content-Type: application/json")
    @POST("/immigration/api/verifyOtp")
    fun verifyOtp(@Body body: Map<String, String>): Call<ResponseModel>

    @Headers("Content-Type: application/json")
    @POST("/immigration/api/resendOtp")
    fun resendOtp(@Body body: Map<String, String>): Call<ResponseModel>


    @Multipart
    @POST("/immigration/api/updateProfile")
    fun postImage(@Header ("accessToken") accessToken:String,
                  @Part image: MultipartBody.Part,
                  @Part("firstName") firstName: String,
                  @Part("lastName") lastName: String,
                  @Part("contact") contact: String,
                  @Part("countryCode") countryCode: String
                 ): Call<ResponseModel>


*/


/*

    @get:GET("/avatar_1.json")
    val avatar1: Call<JSONObject>

    @FormUrlEncoded
    @POST("/")
    fun Save(@Field("answer") name:String,
             @Field("Date") Date:String):Call<JSONObject>
*/


}