package com.napoleontest.presentation.main

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.napoleontest.databinding.HeaderItemOfferBinding
import com.napoleontest.databinding.ItemOfferBinding
import com.napoleontest.util.Util
import java.util.*
import kotlin.properties.Delegates

class OfferAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mOfferList: List<OfferViewContainer> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

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

    override fun getItemCount() = mOfferList.size

    override fun getItemViewType(position: Int): Int {
        return if (mOfferList[position].isHeader) {
            RowType.HEADER.ordinal
        } else {
            RowType.ROW.ordinal
        }
    }

    class ItemViewHolder(private val binding: ItemOfferBinding) :
        RecyclerView.ViewHolder(binding.root),
        Bind {

        override fun bind(container: OfferViewContainer) {
            val offer = container.offer
            if (offer != null) {
                binding.tvOfferTitle.text = offer.name
                binding.tvOfferDesc.text = offer.desc
                Util.displayImage(
                    binding.root.context,
                    offer.image,
                    binding.ivOfferImage
                )
                if (offer.isProduct()) {
                    binding.tvOfferDiscount.visibility = View.VISIBLE
                    binding.tvOfferDiscount.text = "-" + offer.discountInt() + "%"
                    binding.tvOfferPrice.visibility = View.VISIBLE
                    binding.tvOfferPrice.text = offer.getDiscountPrice().toString() + "₽"
                    binding.tvOfferPriceOld.visibility = View.VISIBLE
                    binding.tvOfferPriceOld.text =  offer.price.toInt().toString() + "₽"
                    binding.tvOfferPriceOld.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG;
                    binding.ivBasket.visibility = View.VISIBLE
                } else {
                    binding.tvOfferDiscount.visibility = View.GONE
                    binding.tvOfferDiscount.text = ""
                    binding.tvOfferPrice.visibility = View.GONE
                    binding.tvOfferPrice.text = ""
                    binding.tvOfferPriceOld.visibility = View.GONE
                    binding.tvOfferPriceOld.text = ""
                    binding.ivBasket.visibility = View.GONE
                }
            }
        }

    }

    class HeaderViewHolder(val binding: HeaderItemOfferBinding) :
        RecyclerView.ViewHolder(binding.root), Bind {

        override fun bind(container: OfferViewContainer) {
            binding.headerTextView.text = container.headerTitle
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Bind) holder.bind(mOfferList[position])
    }

    interface Bind {
        fun bind(container: OfferViewContainer)
    }

}