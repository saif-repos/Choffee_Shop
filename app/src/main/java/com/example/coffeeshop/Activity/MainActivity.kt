//package com.example.coffeeshop.Activity

//import android.os.Bundle
//import android.view.View
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.bumptech.glide.Glide
//import com.example.coffeeshop.R
//import com.example.coffeeshop.ViewModel.MainViewModel
//import com.example.coffeeshop.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//    private val viewModel: MainViewModel by viewModels() // Correct initialization
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        initBanner()
//
//    }
//
//    private fun initBanner() {
//        binding.progressBarBanner.visibility = View.VISIBLE
//        viewModel.loadBanner().observeForever {
//            Glide.with(this@MainActivity).load(it[0].url).into(binding.banner)
//            binding.progressBarBanner.visibility = View.GONE
//        }
//        viewModel.loadBanner()
//    }
//}

package com.example.coffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffeeshop.Adapter.CategoryAdapter
import com.example.coffeeshop.Adapter.PopularAdapter
import com.example.coffeeshop.ViewModel.MainViewModel
import com.example.coffeeshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        loadData()
        initCategory()
        initPopular()
        initBottomMenu()

    }

    private fun initBottomMenu() {
        binding.cartbtn.setOnClickListener{
            startActivity(Intent(this,CartActivity::class.java))
        }
    }

    private fun initPopular() {

        binding.progressbarPopular.visibility = View.VISIBLE
        viewModel.loadPopular().observeForever{
            binding.recyclerViewPopular.layoutManager=GridLayoutManager(this,2)
            binding.recyclerViewPopular.adapter=PopularAdapter(it)
            binding.progressbarPopular.visibility=View.GONE
        }
        viewModel.loadPopular()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility =
            View.VISIBLE  // Fixed typo: 'biniding' -> 'binding'
        viewModel.loadCategory()
            .observe(this) { categories ->  // Changed to lifecycle-aware observe
                categories?.let {
                    binding.catagoryView.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    binding.catagoryView.adapter = CategoryAdapter(it) { clickedCategory ->
                        // Handle category item click if needed
                    }
                    binding.progressBarCategory.visibility = View.GONE
                } ?: run {
                    binding.progressBarCategory.visibility = View.GONE
                }
            }
    }

    private fun setupObservers() {
        viewModel.banners.observe(this) { banners ->
            banners?.takeIf { it.isNotEmpty() }?.let {
                Glide.with(this)
                    .load(it[0].url)
                    .into(binding.banner)
                binding.progressBarBanner.visibility = View.GONE
            } ?: run {
                // Handle empty state
                binding.progressBarBanner.visibility = View.GONE
            }
        }
    }

    private fun loadData() {
        binding.progressBarBanner.visibility = View.VISIBLE
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.loadBanner()
        viewModel.loadCategory()
    }
}
