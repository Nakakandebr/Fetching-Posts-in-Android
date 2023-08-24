package com.assessment.fetchingposts.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assessment.fetchingposts.databinding.PostsListItemBinding


class  PostsAdapter (var postsList:List<Posts>):RecyclerView.Adapter<PostsViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding =
            PostsListItemBinding .inflate(LayoutInflater.from(parent.context),parent ,false)
        return PostsViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        var currentPost =postsList[position]
        var  binding=holder.binding
        binding.tvId.text=currentPost.userId.toString()
        binding.tvid.text = currentPost.id.toString()
        binding.tvtitle.text=currentPost.title
        binding.tvbody.text = currentPost.body



    }
    override fun getItemCount(): Int {
        return postsList.size
    }
}
class PostsViewHolder( var binding:PostsListItemBinding): RecyclerView.ViewHolder(binding.root)