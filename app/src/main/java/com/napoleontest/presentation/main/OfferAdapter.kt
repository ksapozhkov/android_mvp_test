package com.napoleontest.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.napoleontest.databinding.HeaderItemOfferBinding
import com.napoleontest.databinding.ItemOfferBinding
import com.napoleontest.domain.model.Offer


class OfferAdapter(private val dataset: MutableList<RecyclerViewContainer>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RowType.ROW.ordinal -> {
                ItemViewHolder(
                    ItemOfferBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                HeaderViewHolder(
                    HeaderItemOfferBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount() = dataset.size

    override fun getItemViewType(position: Int): Int {
        return if (dataset[position].isHeader) {
            RowType.HEADER.ordinal
        } else {
            RowType.ROW.ordinal
        }
    }

    class ItemViewHolder(val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root),
        Bind {

        override fun bind(container: RecyclerViewContainer) {
            val offer = container.offer
            if (offer != null) {
                binding.tvOfferTitle.text = offer.title
                binding.tvOfferDesc.text = offer.desc
            }

        }

    }

    class HeaderViewHolder(val binding: HeaderItemOfferBinding) :
        RecyclerView.ViewHolder(binding.root), Bind {

        override fun bind(container: RecyclerViewContainer) {
            binding.headerTextView.text = container.headerTitle
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataset[position]
        if (holder is Bind) {
            holder.bind(item)
        }
    }

    interface Bind {
        fun bind(container: RecyclerViewContainer)
    }

}