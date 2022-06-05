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

class ProductAdapter :
    ListAdapter<ProductData, ProductAdapter.ProductViewHolder>(ProductDiffUtil) {


    private var onItemClickListener: ((ProductData, Int) -> Unit)? = null
    private var OnButtonAddToCartListener: ((ProductData) -> Unit)? = null
//    private var minusListener: ((Int) -> Unit)? = null
//    private var plusListener: ((Int) -> Unit)? = null

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(
                    getItem(absoluteAdapterPosition),
                    absoluteAdapterPosition
                )
            }

            binding.buttonAddToCart.setOnClickListener {
                OnButtonAddToCartListener?.invoke(getItem(absoluteAdapterPosition))
            }


//            binding.buttonPlus.setOnClickListener {
//                var count = binding.textPieces.text.toString().toInt()
//                count++
//                binding.productPrice.text =
//                    (getItem(absoluteAdapterPosition).price * count).toString()
//                binding.textPieces.text = count.toString()
//                plusListener?.invoke(count)
//            }
//
//            binding.buttonCheck.setOnClickListener {
//                binding.buttonAddToCart.visibility = View.VISIBLE
//                binding.containerPieces.visibility = View.GONE
//                checkListener?.invoke(getItem(absoluteAdapterPosition))
//            }
        }

        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                binding.productName.text = title
                binding.productPrice.text = price.toString()
                Glide
                    .with(binding.productImage)
                    .load(image)
                    .placeholder(R.drawable.placeholder)
                    .into(binding.productImage)
            }
        }

    }

    private object ProductDiffUtil : DiffUtil.ItemCallback<ProductData>() {
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
            ItemProductBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind()
    }

    fun setOnItemClickListener(block: ((ProductData, Int) -> Unit)) {
        onItemClickListener = block
    }


    fun setOnButtonAddToCartListener(block: (ProductData) -> Unit) {
        OnButtonAddToCartListener = block
    }

//    fun setMinusListener(block: (Int) -> Unit) {
//        minusListener = block
//    }
//
//    fun setPlusListener(block: (Int) -> Unit) {
//        plusListener = block
//    }

}