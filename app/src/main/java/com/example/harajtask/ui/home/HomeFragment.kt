package com.example.harajtask.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harajtask.MainActivity
import com.example.harajtask.R
import com.example.harajtask.model.Item
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModel<HomeViewModel>()

    private val dataAdapter: DataAdapter by lazy {
        DataAdapter(this::navigateToDetails)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvList?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
            this.adapter = dataAdapter
        }

        (activity as? MainActivity)?.updateToolBar(resources.getString(R.string.app_name))
        setHasOptionsMenu(true)

        with(viewModel) {
            fetchData()
            dataLoading.observe(viewLifecycleOwner, {
                pbLoading?.isVisible = it
            })

            dataItems.observe(viewLifecycleOwner, {
                dataAdapter.setData(it)
                rvList?.isVisible = true
            })

            filterItems.observe(viewLifecycleOwner, {
                dataAdapter.setData(it)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filterData(newText)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun navigateToDetails(dataItem: Item) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                dataItem
            )
        )
    }
}