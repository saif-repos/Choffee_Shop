package com.example.coffeeshop.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshop.Adapter.CartAdapter
import com.example.coffeeshop.Helper.ChangeNumberItemsListener
import com.example.coffeeshop.Helper.ManagmentCart
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var managmentCart: ManagmentCart
    private var tax = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        calculateCart()
        setVariable()
        initCartlist()


    }

    private fun initCartlist() {
        binding.apply {
            listView.layoutManager =
                LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)
            listView.adapter = CartAdapter(managmentCart.getListCart(), this@CartActivity)
            object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()

                }

            }
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 15

        // Calculate values
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100.0) / 100.0
        val tax = Math.round(managmentCart.getTotalFee() * percentTax * 100.0) / 100.0
        val total = Math.round((itemTotal + tax + delivery) * 100.0) / 100.0

        // Set values to TextViews
        binding.apply {
            totalfeetxt.text = "$$itemTotal"     // Subtotal
            diliveytxt.text = "$$delivery"       // Delivery
            totaltaxtxt.text = "$$tax"           // Total Tax
            totalAmount.text = "$$total"         // Grand Total
        }
    }


}
