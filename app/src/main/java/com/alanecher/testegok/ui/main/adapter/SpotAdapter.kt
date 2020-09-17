package com.alanecher.testegok.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alanecher.testegok.R
import com.alanecher.testegok.databinding.AdapterSpotBinding
import com.alanecher.testegok.repository.domain.Spotlight

class SpotAdapter :
    RecyclerView.Adapter<SpotAdapter.ViewItemHolder>() {

    var spotlights: List<Spotlight> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewItemHolder {
        val viewBinding: AdapterSpotBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_spot, parent, false
        )
        return ViewItemHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return spotlights.size
    }

    override fun onBindViewHolder(holder: ViewItemHolder, position: Int) {
        holder.onBind(position)
    }

    fun setData(spotlights: List<Spotlight>) {
        this.spotlights = spotlights
        notifyDataSetChanged()
    }

    inner class ViewItemHolder(private val viewBinding: AdapterSpotBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val row = spotlights[position]
            viewBinding.spotlights = row
        }
    }
}