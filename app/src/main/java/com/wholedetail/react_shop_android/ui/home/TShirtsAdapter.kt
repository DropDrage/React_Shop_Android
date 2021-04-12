package com.wholedetail.react_shop_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wholedetail.react_shop_android.R
import com.wholedetail.react_shop_android.model.TShirt

class TShirtsAdapter : RecyclerView.Adapter<TShirtViewHolder>() { //ToDo to paging

    var tShirts = emptyList<TShirt>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TShirtViewHolder {
        return TShirtViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_tshirt,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TShirtViewHolder, position: Int) {
        holder.bind(tShirts[position])
    }

    override fun getItemCount() = tShirts.size
}