package com.digital_pusauli.app


object Constant {

    const val BASE_URL = "http://192.168.43.121:8080"
    const val BASE_URL_Image = "$BASE_URL/api/images/"
    //const val BASE_URL_Image = "http://worklime.com/immigration/images/Image-863835754.jpeg"
    const val DefaultImage = "https://s10.postimg.org/mmadoq6jd/user.png"
    const val BASE_URL2 = "https://raw.githubusercontent.com"




    const val urlCategoryShopAll = "/api/categoryShopAll"
    const val urlRegister_shop = "/api/register_shop"
    const val urlShowAllShop = "/api/showAllShop"

    const val urlVerifyOtp = "/immigration/api/verifyOtp"
    const val urlResendOtp = "/immigration/api/resendOtp"
    const val urlUpdateProfile = "/immigration/api/updateProfile"
    const val urlLogin= "/immigration/api/login"
    const val urlForgotPassword= "/immigration/api/forgotPassword"
    const val urlSetPassword= "/immigration/api/setPassword"
    const val urlChangePassword= "/immigration/api/changePassword"
    const val urlLogout= "/immigration/api/logout"
    const val urlQuestions= "/immigration/api/questions"
    const val urlStatisticReport= "/immigration/api/statisticReport"
    const val urlgetPdfs= "/immigration/api/getPdfs"



    //Key URL  SignUp  && Login && UpdateProfile
    const val key_accessToken ="accessToken"
    const val key_Fname ="firstName"
    const val key_Lname ="lastName"
    const val key_email ="email"
    const val key_contact ="contact"
    const val key_countryCode ="countryCode"
    const val key_password ="password"
    const val key_oldPassword ="oldPassword"

    //EditProfile key
    var key_profilePic="profilePic"

    //OTP key
    var key_type="type"
    var key_userId="userId"
    var key_otp="otp"

    // access value anywhere
    var accessTokenValues=""
    var countryCodeValues=""
    var contactValues=""
    
    
    //validation lenth
    var validationMobLenth=5
    var validationPassLenth=8






}
