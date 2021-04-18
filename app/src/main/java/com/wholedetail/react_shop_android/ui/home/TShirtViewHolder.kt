package com.wholedetail.react_shop_android.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.wholedetail.react_shop_android.base.BindableViewHolder
import com.wholedetail.react_shop_android.databinding.ItemTshirtBinding
import com.wholedetail.react_shop_android.model.TShirtMinified

class TShirtViewHolder(private val binding: ItemTshirtBinding) : RecyclerView.ViewHolder(binding.root),
    BindableViewHolder<TShirtMinified> {

    override fun bind(data: TShirtMinified) {
        binding.apply {
            tShirt = data
            ratingBRB.rating = data.rating
            root.setOnClickListener {
                //ToDo redirect to TShirt screen
            }
            authorNameTV.setOnClickListener {
                //ToDo redirect to User screen
            }
        }
    }

}