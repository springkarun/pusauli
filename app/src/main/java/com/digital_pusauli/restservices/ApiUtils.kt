package com.digital_pusauli.restservices

import com.digital_pusauli.app.Constant.BASE_URL

object ApiUtils {


    val apiService: APIService
        get() = RetrofitClient.getClient(BASE_URL).create(APIService::class.java)
}