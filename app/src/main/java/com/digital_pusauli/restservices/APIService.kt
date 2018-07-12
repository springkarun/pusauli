package com.digital_pusauli.restservices
import com.digital_pusauli.app.Constant.urlCategoryShopAll
import com.digital_pusauli.app.Constant.urlRegister_shop
import com.digital_pusauli.app.Constant.urlShowAllShop
import com.digital_pusauli.model.ResponseModel
import com.digital_pusauli.model.ResponseModels
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface APIService {


    @GET(urlCategoryShopAll)
    fun getCategoryShopAll(): Observable<ResponseModels>


    @Multipart
    @POST(urlRegister_shop)
    fun saveRegisterShop(
                    @Part("categoryId")  categoryId: RequestBody,
                    @Part("shopName") shopName: RequestBody,
                    @Part("shopReg") shopReg: RequestBody,
                    @Part("shopEmail") shopEmail: RequestBody,
                    @Part("shopMobile") shopMobile: RequestBody,
                    @Part("shopAddress") shopAddress: RequestBody,
                    @Part("shopNearst") shopNearst: RequestBody,
                    @Part("shopTime") shopTime: RequestBody,
                    @Part("shopRating") shopRating: RequestBody,
                    @Part("shopLatitude") shopLatitude: RequestBody,
                    @Part("shopLongitude") shopLongitude: RequestBody,
                    @Part("ownerName") ownerName: RequestBody,
                    @Part("ownerEmail") ownerEmail: RequestBody,
                    @Part("ownerContact") ownerContact: RequestBody,
                    @Part("colorCode") colorCode: RequestBody,
                    @Part ownerAvatar: MultipartBody.Part,
                    @Part shopAvatar: MultipartBody.Part): Observable<ResponseModels>

    @GET(urlShowAllShop)
    fun getShowShopAll(): Observable<ResponseModels>


   /* @POST("/categrory_details.php")
    @FormUrlEncoded
    fun getCategrory(@Field("category_id") catg_name: String): Observable<ResponseModel>
*/

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