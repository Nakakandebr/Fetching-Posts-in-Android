package com.assessment.fetchingposts.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessment.fetchingposts.models.Posts
import com.assessment.fetchingposts.repository.PostsRepository
import kotlinx.coroutines.launch

class PostsViewModel:ViewModel() {
    var postsRepo = PostsRepository()
    val postsLiveData  = MutableLiveData<List<Posts>>()
    val errorLiveData = MutableLiveData<String>()


    fun fetchPosts(){
        viewModelScope.launch {
            val response = postsRepo.getPosts()
            if (response.isSuccessful){
                val postsLists = response.body() ?: emptyList()

                postsLiveData.postValue(postsLists as List<Posts>)
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

}
