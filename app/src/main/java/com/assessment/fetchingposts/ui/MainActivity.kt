package com.assessment.fetchingposts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assessment.fetchingposts.api.ApiClient
import com.assessment.fetchingposts.api.ApiInterface
import com.assessment.fetchingposts.databinding.ActivityMainBinding



import com.assessment.fetchingposts.viewmodels.PostsViewModel
i
class MainActivity : AppCompatActivity() {

    private val postsViewModel: PostsViewModel  by viewModels()


    private lateinit var postsAdapter: PostsAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.rvPosts
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager
        productAdapter = PostsAdapter(emptyList())
        recyclerView.adapter = productAdapter
    }

    override fun onResume() {
        super.onResume()
        postsViewModel.fetchProducts()
        postsViewModel.postsLiveData.observe(this, Observer { productList ->


            if (productList != null) {
                postsAdapter.updateProducts(productList)
            }
            Toast.makeText(
                baseContext,
                "Fetched ${productList?.size} products",
                Toast.LENGTH_LONG
            ).show()

        })

        postsViewModel.errorLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG)
                .show()


        })
    }
}