package com.wholedetail.react_shop_android.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.wholedetail.react_shop_android.base.BindableViewHolder
import com.wholedetail.react_shop_android.databinding.ItemTshirtBinding
import com.wholedetail.react_shop_android.model.TShirt

class TShirtViewHolder(private val binding: ItemTshirtBinding) : RecyclerView.ViewHolder(binding.root),
    BindableViewHolder<TShirt> {

    override fun bind(data: TShirt) {
        binding.tShirt = data
    }

}