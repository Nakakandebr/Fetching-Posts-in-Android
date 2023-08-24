
package com.assessment.fetchingposts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assessment.fetchingposts.databinding.ActivityMainBinding
import com.assessment.fetchingposts.models.PostsAdapter
import com.assessment.fetchingposts.viewmodels.PostsViewModel


class MainActivity : AppCompatActivity() {

    private val postsViewModel: PostsViewModel by viewModels()
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observePosts()
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = binding.rvPosts
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager
        postsAdapter = PostsAdapter(emptyList())
        recyclerView.adapter = postsAdapter
    }

    private fun observePosts() {
        postsViewModel.postsLiveData.observe(this, Observer { postList ->
            postList?.let {
                postsAdapter.updateProducts(it)
                Toast.makeText(
                    baseContext,
                    "Fetched ${it.size} posts",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        postsViewModel.errorLiveData.observe(this, Observer { error ->
            error?.let {
                Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
            }
        })


        postsViewModel.fetchPosts()
    }
}
