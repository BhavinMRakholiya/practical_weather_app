package com.practiclewatheraapp.ui.fragment.home

import com.practiclewatheraapp.R
import com.practiclewatheraapp.databinding.ItemCityBinding
import com.practiclewatheraapp.source.entity.CityData
import com.practiclewatheraapp.ui.base.BaseRecyclerViewAdapter


class CityAdapter(private val onCityClick: (CityData,Boolean) -> Unit) : BaseRecyclerViewAdapter<ItemCityBinding>() {
    private val listUsers = arrayListOf<CityData>()

    override fun setLayoutItem() = R.layout.item_city

    override fun setCount() = listUsers.size

    override fun onViewReady(holder: ViewHolder<ItemCityBinding>, position: Int) {
        holder.mBinding.cityData = listUsers[position]
        holder.itemView.setOnClickListener {
            onCityClick.invoke(listUsers[position],false)
        }
        holder.itemView.setOnLongClickListener {
            onCityClick.invoke(listUsers[position],true)
            true
        }
    }

    fun updateData(listUsers: List<CityData>) {
        this.listUsers.clear()
        this.listUsers.addAll(listUsers)
        notifyDataSetChanged()
    }
}