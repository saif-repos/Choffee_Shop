package com.example.coffeeshop.Adapter

import android.content.Context
import android.graphics.ColorSpace.Model
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.coffeeshop.Domain.itemsModel
import com.example.coffeeshop.Helper.ChangeNumberItemsListener
import com.example.coffeeshop.Helper.ManagmentCart
import com.example.coffeeshop.databinding.ViewholderCartBinding

class CartAdapter(
    private val listItemSelected: ArrayList<itemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener? = null


) : RecyclerView.Adapter<CartAdapter.Viewholder>() {
    class Viewholder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root)


    private val managmentCart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.Viewholder {
        val binding =
            ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.Viewholder, position: Int) {
        val item = listItemSelected[position]
        holder.binding.titletxt.text = item.title
        holder.binding.itemfeeEachItem.text = "$" + item.price
        holder.binding.totaleachItem.text = "$" + (item.numberInCart * item.price).toString()
        holder.binding.numberInCartTxt.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context).load(item.picUrl[0]).apply(
            RequestOptions().transform(
                CenterCrop(), RoundedCorners(16)
            )
        ).into(holder.binding.picCart)


        holder.binding.plusbtn.setOnClickListener {
            managmentCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })

        }
        holder.binding.minusbtn.setOnClickListener {
            managmentCart.minusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }
        holder.binding.removeItembtn.setOnClickListener {
            managmentCart.romveItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }


    }


    override fun getItemCount(): Int = listItemSelected.size


}