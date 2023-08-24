package com.assessment.fetchingposts.api




import retrofit2.Call
import com.assessment.fetchingposts.models.Posts

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/posts")
    suspend fun getPosts():Response<Posts>

    @GET ("posts/{id}")
    suspend fun getPostById(@Path("id")productId:Int): Response<Posts>


}