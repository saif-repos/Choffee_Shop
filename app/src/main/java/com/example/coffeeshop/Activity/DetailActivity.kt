package com.example.coffeeshop.Activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.coffeeshop.Domain.itemsModel
import com.example.coffeeshop.Helper.ManagmentCart
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var managmentCart: ManagmentCart
    private var item: itemsModel? = null   // Nullable now

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)
        setupBundle()
        intiSizeList()
    }

    private fun intiSizeList() {
        binding.apply {
            smallBtn.setOnClickListener {
                smallBtn.setBackgroundResource(R.drawable.brown_storke_bg)
                mediumBtn.setBackgroundResource(0)
                largerBtn.setBackgroundResource(0)
            }
            mediumBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(R.drawable.brown_storke_bg)
                largerBtn.setBackgroundResource(0)
            }
            largerBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(0)
                largerBtn.setBackgroundResource(R.drawable.brown_storke_bg)
            }


        }

    }




    private fun setupBundle() {
        // Log all keys in the intent
        val keys = intent.extras?.keySet()
        Log.d("DetailActivity", "Received keys: $keys")

        // Safe cast
        item = intent.getSerializableExtra("item") as? itemsModel
        if (item == null) {
            Log.e("DetailActivity", "No item passed to DetailActivity")
            finish() // Close activity if no data
            return
        }

        binding.apply {
            // Safe image loading
            val imageUrl = item?.picUrl?.firstOrNull()
            Glide.with(this@DetailActivity).load(imageUrl).into(picMain)

            titlebtn.text = item?.title
            descriptiontxt.text = item?.description
            AddtoCartPricetxt.text = "$${item?.price}"
            ratingtxt.text = "$${item?.rating}"

            Addtocartbtn.setOnClickListener {
                item?.let {
                    it.numberInCart = numberInCartTxt.text.toString().toIntOrNull() ?: 0
                    managmentCart.insertItems(it)
                }
            }
            backbtn.setOnClickListener { finish() }
            plusbtn.setOnClickListener {
                item?.let {
                    it.numberInCart++
                    numberInCartTxt.text = it.numberInCart.toString()
                }
            }
            minusbtn.setOnClickListener {
                item?.let {
                    if (it.numberInCart > 0) {
                        it.numberInCart--
                        numberInCartTxt.text = it.numberInCart.toString()
                    }
                }
            }
        }
    }
}
