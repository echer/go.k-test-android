package com.alanecher.testegok.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alanecher.testegok.R
import com.alanecher.testegok.databinding.AdapterProductBinding
import com.alanecher.testegok.repository.domain.Product

class ProductAdapter :
    RecyclerView.Adapter<ProductAdapter.ViewItemHolder>() {

    var products: List<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewItemHolder {
        val viewBinding: AdapterProductBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_product, parent, false
        )
        return ViewItemHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewItemHolder, position: Int) {
        holder.onBind(position)
    }

    fun setData(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    inner class ViewItemHolder(private val viewBinding: AdapterProductBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val row = products[position]
            viewBinding.products = row
        }
    }
}