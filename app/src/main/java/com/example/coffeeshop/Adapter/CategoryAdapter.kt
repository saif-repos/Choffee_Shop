//package com.example.coffeeshop.Adapter
//
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.coffeeshop.Domain.CategoryModel
//import com.example.coffeeshop.databinding.ViewholderCategoryBinding
//
//class CategoryAdapter(val items: MutableList<CategoryModel>) :
//    RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
//        inner class Viewholder(val binding: ViewholderCategoryBinding): RecyclerView.ViewHolder() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
//        TODO("Not yet implemented")
//    }
//
//    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }
//
//}
//

package com.example.coffeeshop.Adapter

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.Activity.ItemsListActivity
import com.example.coffeeshop.Domain.CategoryModel
import com.example.coffeeshop.databinding.ViewholderCategoryBinding

class CategoryAdapter(
    private val items: List<CategoryModel>,
    private val onItemClick: (CategoryModel) -> Unit = {}
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private lateinit var context: Context
    private var selectedPosition = -1
    private var lastSelectedPosition = -1


    inner class ViewHolder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleCat.text = item.title
        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            Handler(Looper.getMainLooper()).postDelayed({

                val intent = Intent(context, ItemsListActivity::class.java).apply {
                    putExtra("id", item.id.toString())
                    putExtra("title", item.title)
                }
                ContextCompat.startActivity(context,intent,null)

            }, 500)

        }
        if (selectedPosition==position){
            holder.binding.titleCat.setBackgroundResource(com.example.coffeeshop.R.drawable.grey_full_corner_bg)
            holder.binding.titleCat.setTextColor(context.resources.getColor(com.example.coffeeshop.R.color.white))
        }
        else{
            holder.binding.titleCat.setBackgroundResource(com.example.coffeeshop.R.drawable.white_full_corner_bg)
            holder.binding.titleCat.setTextColor(context.resources.getColor(com.example.coffeeshop.R.color.darkBrown))
        }
    }

        override fun getItemCount(): Int = items.size

        // Optional: Update data method
        fun updateData(newItems: List<CategoryModel>) {
            (items as? MutableList)?.apply {
                clear()
                addAll(newItems)
            }
            notifyDataSetChanged()
        }
    }
