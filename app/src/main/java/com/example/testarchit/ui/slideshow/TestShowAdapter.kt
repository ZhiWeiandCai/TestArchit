package com.example.testarchit.ui.slideshow

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testarchit.R
import com.example.testarchit.databinding.ItemTestShowBinding

/**
 * Created by caizhiwei on 2023/7/26
 */
class TestShowAdapter : ListAdapter<TableDish, TestShowViewHolder>(TestShowDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestShowViewHolder {
        val binding = ItemTestShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //val binding = ItemTestShowBinding.inflate(LayoutInflater.from(parent.context))
        return TestShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TestShowViewHolder, position: Int) {
        holder.textView.text = getItem(position).tableName
        holder.imageView.setImageDrawable(
            ResourcesCompat.getDrawable(holder.imageView.resources, R.mipmap.ic_launcher, null)
        )
        val innerAdapterDish = DishAdapter()
        holder.rw.adapter = innerAdapterDish
        innerAdapterDish.submitList(getItem(position).dishes)
    }
}

class TestShowViewHolder(binding: ItemTestShowBinding) : RecyclerView.ViewHolder(binding.root) {
    val imageView: ImageView = binding.imageViewItemShow
    val textView: TextView = binding.textViewItemShow
    val rw: RecyclerView = binding.rvDish
}

private class TestShowDiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<TableDish>() {
    override fun areItemsTheSame(oldItem: TableDish, newItem: TableDish): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TableDish, newItem: TableDish): Boolean {
        return oldItem == newItem
    }
}