package com.example.harajtask.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.signature.ObjectKey
import com.example.harajtask.R
import com.example.harajtask.model.Item
import com.example.harajtask.utils.getInterval
import com.example.harajtask.utils.toHtml
import kotlinx.android.synthetic.main.item_data.view.*

class DataAdapter(var onItemSelect: (dataItem: Item) -> Unit) :
    RecyclerView.Adapter<DataAdapter.DataAdapterViewHolder>() {

    private val adapterData = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_data, parent, false)

        return DataAdapterViewHolder(view, this::onItemClick)
    }

    override fun onBindViewHolder(holder: DataAdapterViewHolder, position: Int) {
        holder.bind(adapterData[position], position)
    }

    override fun getItemCount(): Int = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return position
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Item>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    private fun onItemClick(index: Int) {
        onItemSelect.invoke(adapterData[index])
    }

    class DataAdapterViewHolder(
        itemView: View,
        private val onItemSelect: (index: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onItemSelect.invoke(adapterPosition) }
        }

        fun bind(dataModel: Item, position: Int) {
            with(itemView) {
                val color = if (position % 2 == 0) R.color.colorFEFFFE else R.color.colorF9FBFF
                clMain?.setCardBackgroundColor(
                    ContextCompat.getColor(
                        clMain.context,
                        color
                    )
                )
                ivItem?.let {
                    Glide.with(it.context)
                        .load(dataModel.thumbURL)
                        .transform(CenterCrop(), RoundedCorners(32))
                        .signature(ObjectKey(dataModel.thumbURL))
                        .dontAnimate()
                        .into(it)
                }
                if (dataModel.commentCount > 0) {
                    tvComments.text = buildString {
                        append("(")
                        append(dataModel.commentCount)
                        append(")")
                    }
                    tvComments.visibility = View.VISIBLE
                }
                tvTitle?.text = dataModel.title.toHtml()
                tvTime?.text = dataModel.date.toLong().getInterval()
                tvLocation?.text = dataModel.city
                tvUser?.text = dataModel.username
            }
        }
    }
}