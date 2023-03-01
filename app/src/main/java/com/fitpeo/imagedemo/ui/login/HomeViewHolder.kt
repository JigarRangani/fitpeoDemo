package com.fitpeo.imagedemo.ui.login

import androidx.recyclerview.widget.RecyclerView
import com.fitpeo.imagedemo.databinding.ItemHomeImageBinding
import com.squareup.picasso.Picasso

class HomeViewHolder(var binding: ItemHomeImageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setData(item: ImageDataResponseItem, homeContracts: HomeContracts) {
        item.run {
            binding.run {
                Picasso.get()
                    .load(url)
                    .into(imgHome)
                root.setOnClickListener {
                    homeContracts.onImageItemClick(item)
                }
            }
        }
    }

}