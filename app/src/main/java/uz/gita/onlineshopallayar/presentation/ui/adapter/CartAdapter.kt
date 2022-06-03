package uz.gita.onlineshopallayar.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import timber.log.Timber
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity
import uz.gita.onlineshopallayar.databinding.ItemCartBinding


class CartAdapter : ListAdapter<ProductResponseEntity, CartAdapter.CartViewHolder>(CartDiffUtil) {

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

        }

        fun bind() {
                Timber.tag("BBB").d(getItem(absoluteAdapterPosition).toString())
            getItem(absoluteAdapterPosition).apply {
                binding.productName.text = title
                binding.textPrice.text = price.toString()
                Glide.with(binding.productImage)
                    .load(image)
                    .into(binding.productImage)
            }
        }
    }

    private object CartDiffUtil : DiffUtil.ItemCallback<ProductResponseEntity>() {
        override fun areItemsTheSame(
            oldItem: ProductResponseEntity,
            newItem: ProductResponseEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductResponseEntity,
            newItem: ProductResponseEntity
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            ItemCartBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind()
    }

}