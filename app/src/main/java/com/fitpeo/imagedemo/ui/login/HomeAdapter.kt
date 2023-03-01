package com.fitpeo.imagedemo.ui.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fitpeo.imagedemo.databinding.ItemHomeImageBinding

class HomeAdapter : RecyclerView.Adapter<HomeViewHolder>() {

    private lateinit var binding: ItemHomeImageBinding

    var data: MutableList<ImageDataResponseItem>? = arrayListOf()

    lateinit var homeContracts: HomeContracts

    fun updateItems(newItems: List<ImageDataResponseItem>?) {
        val oldItems = ArrayList(this.data!!)
        this.data!!.clear()
        if (newItems != null) {
            this.data!!.addAll(newItems)
        }
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return oldItems.size
            }

            override fun getNewListSize(): Int {
                return data!!.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldItems[oldItemPosition] == newItems!![newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldItems[oldItemPosition] == newItems!![newItemPosition]
            }
        }).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemHomeImageBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        data?.get(position)?.let {
            holder.setData(it,homeContracts)
        }
    }

    override fun getItemCount(): Int = (data?.size) ?: 0

}