//package com.example.coffeeshop.Activity
//
//import android.os.Bundle
//import android.view.View
//import androidx.activity.enableEdgeToEdge
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.lifecycle.Observer
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.coffeeshop.Adapter.ItemListCategoryAdapter
//import com.example.coffeeshop.R
//import com.example.coffeeshop.ViewModel.MainViewModel
//import com.example.coffeeshop.databinding.ActivityItemsListBinding
//import com.example.coffeeshop.databinding.ViewholderItemsListBinding
//
//class ItemsListActivity : AppCompatActivity() {
//    lateinit var binding: ActivityItemsListBinding
//    private val viewModel: MainViewModel by viewModels()
//    private var id: String = ""
//    private var title: String = ""
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        binding = ActivityItemsListBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        getBundels()
//        initList()
//
//
//    }
//
//    private fun initList() {
//        binding.apply {
//            progressBar.visibility = View.VISIBLE
//            viewModel.loadItem(id).observe(this@ItemsListActivity, Observer {
//
//                ListView.layoutManager = GridLayoutManager(this@ItemsListActivity, 2)
//                ListView.adapter = ItemListCategoryAdapter(it)
//
//                progressBar.visibility = View.GONE
//            })
//            binding.backbtn.setOnClickListener {
//                finish()
//            }
//
//
//        }
//    }
//
//    private fun getBundels() {
//        id = intent.getStringExtra("id")!!
//        title = intent.getStringExtra("title")!!
//        binding.CategortTxt.text = title
//
//    }
//}

package com.example.coffeeshop.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.Adapter.ItemListCategoryAdapter
import com.example.coffeeshop.R
import com.example.coffeeshop.ViewModel.MainViewModel
import com.example.coffeeshop.databinding.ActivityItemsListBinding
import com.example.coffeeshop.databinding.ViewholderItemsListBinding

class ItemsListActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemsListBinding
    private val viewModel: MainViewModel by viewModels()
    private var id: String = ""
    private var title: String = ""
    private lateinit var adapter: ItemListCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundles()
        setupRecyclerView()
        initList()
        setupBackButton()
    }

    private fun setupRecyclerView() {
        // Initialize adapter with empty list first
        adapter = ItemListCategoryAdapter(mutableListOf())
        binding.ListView.layoutManager = GridLayoutManager(this, 2)
        binding.ListView.adapter = adapter
    }

    private fun initList() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.loadItem(id).observe(this, Observer { items ->
            // Update the adapter with new data
            adapter.items.clear()
            adapter.items.addAll(items)
            adapter.notifyDataSetChanged()

            binding.progressBar.visibility = View.GONE
        })
    }

    private fun setupBackButton() {
        binding.backbtn.setOnClickListener {
            finish()
        }
    }

    private fun getBundles() {
        id = intent.getStringExtra("id") ?: ""
        title = intent.getStringExtra("title") ?: ""
        binding.CategortTxt.text = title
    }
}


