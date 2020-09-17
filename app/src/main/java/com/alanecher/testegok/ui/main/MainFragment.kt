package com.alanecher.testegok.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alanecher.testegok.R
import com.alanecher.testegok.databinding.MainFragmentBinding
import com.alanecher.testegok.repository.domain.Resource
import com.alanecher.testegok.repository.domain.dto.ProductsDTO
import com.alanecher.testegok.ui.main.adapter.ImageLoader
import com.alanecher.testegok.ui.main.adapter.ProductAdapter
import com.alanecher.testegok.ui.main.adapter.SpotAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var mDataBinding: MainFragmentBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var spotAdapter: SpotAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        val rootView = mDataBinding.root
        mDataBinding.lifecycleOwner = this
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter()
        recyclerProduct.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerProduct.adapter = productAdapter

        spotAdapter = SpotAdapter()
        recyclerSpot.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerSpot.adapter = spotAdapter
    }

    private fun setupObservers() {
        viewModel.products.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it
                    progressBar.visibility = View.GONE
                    if ((it.data as ProductsDTO).products.isNotEmpty()) productAdapter.setData(ArrayList(
                        it.data.products))
                    if ((it.data as ProductsDTO).spotlights.isNotEmpty()) spotAdapter.setData(ArrayList(
                        it.data.spotlights))
                    ImageLoader.loadImage(imgCash, it.data.cash.bannerURL)
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    progressBar.visibility = View.VISIBLE
            }
        })
    }
}