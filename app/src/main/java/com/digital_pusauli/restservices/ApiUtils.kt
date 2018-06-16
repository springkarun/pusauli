package com.digital_pusauli.restservices

object ApiUtils {

    val BASE_URL = "http://api.canvatechnoloy.in"
    val apiService: APIService
        get() = RetrofitClient.getClient(BASE_URL).create(APIService::class.java)
}