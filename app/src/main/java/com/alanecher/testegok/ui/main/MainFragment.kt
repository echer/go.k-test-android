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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var mDataBinding: MainFragmentBinding
    //private lateinit var adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        val rootView = mDataBinding.root
        mDataBinding.lifecycleOwner = this
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        //adapter = CharactersAdapter(this)
        mDataBinding.recyclerProduct.layoutManager = LinearLayoutManager(requireContext())
        //mDataBinding.recyclerProduct.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.products.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it
                    mDataBinding.progressBar.visibility = View.GONE
                    //if ((it.data as ProductsDTO).products.isNotEmpty()) adapter.setItems(ArrayList((it.data as ProductsDTO).products))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    mDataBinding.progressBar.visibility = View.VISIBLE
            }
        })
    }
}