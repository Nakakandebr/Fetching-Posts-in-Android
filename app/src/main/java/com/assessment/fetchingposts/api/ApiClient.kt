package com.assessment.fetchingposts.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var  retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <B> buildClient(apiInterface: Class<B>):B{
        return retrofit.create(apiInterface)
    }
}
