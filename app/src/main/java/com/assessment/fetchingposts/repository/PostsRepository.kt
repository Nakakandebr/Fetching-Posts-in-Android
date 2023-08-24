package com.assessment.fetchingposts.repository


import com.assessment.fetchingposts.api.ApiClient
import com.assessment.fetchingposts.api.ApiInterface

import com.assessment.fetchingposts.models.Posts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostsRepository {
    val apiClient = ApiClient.buildClient(ApiInterface::class.java)

    suspend fun  getPosts():Response <Posts>{
        return withContext(Dispatchers.IO){
            apiClient.getPosts()
        }

    }
}