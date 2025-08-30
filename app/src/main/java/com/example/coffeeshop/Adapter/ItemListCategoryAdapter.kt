package com.example.coffeeshop.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeshop.Activity.DetailActivity
import com.example.coffeeshop.Domain.itemsModel
import com.example.coffeeshop.databinding.ViewholderItemsListBinding


class ItemListCategoryAdapter(val items: MutableList<itemsModel>) :
    RecyclerView.Adapter<ItemListCategoryAdapter.Viewholder>() {

    lateinit var context: Context

    class Viewholder(val binding: ViewholderItemsListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListCategoryAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderItemsListBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: ItemListCategoryAdapter.Viewholder, position: Int) {
        holder.binding.titletxt.text = items[position].title
        holder.binding.pricetxt.text = "$" + items[position].price.toString()
        holder.binding.subtitletxt.text = items[position].extra.toString()

        Glide.with(context).load(items[position].picUrl[0]).into(holder.binding.pic)
        holder.itemView.setOnClickListener{
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("item", items[position])
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = items.size

}

//package com.example.coffeeshop.Adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.coffeeshop.Domain.itemsModel
//import com.example.coffeeshop.databinding.ViewholderItemsListBinding
//
//class ItemListCategoryAdapter(private var items: List<itemsModel>) :
//    RecyclerView.Adapter<ItemListCategoryAdapter.Viewholder>() {
//
//    fun updateList(newList: List<itemsModel>) {
//        items = newList
//        notifyDataSetChanged()
//    }
//
//    class Viewholder(val binding: ViewholderItemsListBinding) :
//        RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
//        val binding = ViewholderItemsListBinding.inflate(
//            LayoutInflater.from(parent.context), parent, false
//        )
//        return Viewholder(binding)
//    }
//
//    override fun onBindViewHolder(holder: Viewholder, position: Int) {
//        val item = items[position]
//        holder.binding.titletxt.text = item.title
//        holder.binding.pricetxt.text = "$${item.price}"
//        holder.binding.subtitletxt.text = item.extra?.toString() ?: ""
//
//        if (item.picUrl.isNotEmpty()) {
//            Glide.with(holder.itemView.context)
//                .load(item.picUrl[0])
//                .into(holder.binding.pic)
//        }
//    }
//
//    override fun getItemCount(): Int = items.size
//}