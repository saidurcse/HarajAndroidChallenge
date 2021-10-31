package com.example.harajtask.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.example.harajtask.MainActivity
import com.example.harajtask.R
import com.example.harajtask.utils.getDate
import com.example.harajtask.utils.toHtml
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val viewModel by viewModel<DetailsViewModel>()
    private val args: DetailsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.updateToolBar("", true)
        setHasOptionsMenu(true)


        with(viewModel) {
            args.dataItem?.let {
                initData(it)
            }
            dataItem.observe(viewLifecycleOwner, { dataItem ->
                ivItem?.let {
                    Glide.with(requireContext())
                        .load(dataItem.thumbURL)
                        .signature(ObjectKey(dataItem.thumbURL))
                        .dontAnimate()
                        .into(it)
                }
                tvTitle?.text = dataItem.title.toHtml()
                tvDescription?.text = dataItem.body.toHtml()
                tvTime?.text = dataItem.date.toLong().getDate()
                tvLocation?.text = dataItem.city
                tvUser?.text = dataItem.username
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_share, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareButton -> {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, viewModel.getBody())
                    putExtra(Intent.EXTRA_SUBJECT, viewModel.getTitle())
                }
                startActivity(Intent.createChooser(intent, "Share item"))
            }
            android.R.id.home -> activity?.onBackPressed()

        }
        return super.onOptionsItemSelected(item)
    }
}