package uz.gita.onlineshopallayar.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.databinding.ItemProductBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var onItemClickListener: ((ProductData) -> Unit)? = null
    private var onButtonAddToCartListener: ((ProductData) -> Unit)? = null


    var list: MutableList<ProductData> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(
                    list[absoluteAdapterPosition]
                )
            }

            binding.buttonAddToCart.setOnClickListener {
                onButtonAddToCartListener?.invoke(list[absoluteAdapterPosition])
            }
        }

        fun bind() {
            list[absoluteAdapterPosition].apply {
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

    override fun getItemCount(): Int = list.size

    fun setOnItemClickListener(block: ((ProductData) -> Unit)?) {
        onItemClickListener = block
    }

    fun setOnButtonAddToCartListener(block: (ProductData) -> Unit) {
        onButtonAddToCartListener = block
    }
}