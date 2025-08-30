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
import com.example.coffeeshop.databinding.ViewholderCategoryBinding
import com.example.coffeeshop.databinding.ViewholderPopularBinding

class PopularAdapter(val items: MutableList<itemsModel>) :
    RecyclerView.Adapter<PopularAdapter.Viewholder>() {

    lateinit var context: Context

    class Viewholder(val binding: ViewholderPopularBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderPopularBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.Viewholder, position: Int) {
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