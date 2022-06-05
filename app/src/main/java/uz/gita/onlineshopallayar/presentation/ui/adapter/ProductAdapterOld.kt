package uz.gita.onlineshopallayar.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.databinding.ItemProductBinding

class ProductAdapterOld :
    ListAdapter<ProductData, ProductAdapterOld.ProductViewHolder>(ProductDiffUtil) {


    private var onItemClickListener: ((ProductData) -> Unit)? = null
    private var onButtonAddToCartListener: ((ProductData) -> Unit)? = null

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(
                    getItem(absoluteAdapterPosition)
                )
            }

            binding.buttonAddToCart.setOnClickListener {
                onButtonAddToCartListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                binding.productName.text = title
                binding.productPrice.text = "$price$"
                Glide
                    .with(binding.productImage)
                    .load(image)
                    .placeholder(R.drawable.placeholder)
                    .into(binding.productImage)
            }
        }

    }

    object ProductDiffUtil : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductData,
            newItem: ProductData
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind()
    }

    fun setOnItemClickListener(block: ((ProductData) -> Unit)?) {
        onItemClickListener = block
    }

    fun setOnButtonAddToCartListener(block: (ProductData) -> Unit) {
        onButtonAddToCartListener = block
    }
}