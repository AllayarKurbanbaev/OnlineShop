package uz.gita.onlineshopallayar.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import timber.log.Timber
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity
import uz.gita.onlineshopallayar.databinding.ItemCartBinding


class CartAdapterOld : ListAdapter<CartEntity, CartAdapterOld.CartViewHolder>(CartDiffUtil) {
    private var minusListener: ((Int) -> Unit)? = null
    private var plusListener: ((Int) -> Unit)? = null
    private var deleteListener: ((CartEntity, Int) -> Unit)? = null

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.buttonMinus.setOnClickListener {
                if (getItem(absoluteAdapterPosition).quantity > 1) {
                    minusListener?.invoke(absoluteAdapterPosition)
                }
            }

            binding.buttonAdd.setOnClickListener {
                plusListener?.invoke(absoluteAdapterPosition)
            }

            binding.buttonDelete.setOnClickListener {
                deleteListener?.invoke(
                    getItem(absoluteAdapterPosition),
                    absoluteAdapterPosition
                )
            }
        }

        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                binding.textPriceTitle.text = "Price : ${productPrice}$"
                binding.productName.text = productName
                binding.textPrice.text = String.format("%.2f", quantity * productPrice) + "$"
                binding.textPieces.text = quantity.toString()
                Glide.with(binding.productImage)
                    .load(image)
                    .placeholder(R.drawable.placeholder)
                    .into(binding.productImage)
            }
        }
    }

    object CartDiffUtil : DiffUtil.ItemCallback<CartEntity>() {
        override fun areItemsTheSame(
            oldItem: CartEntity,
            newItem: CartEntity
        ): Boolean {
            Timber.tag("DDD").d("oldItem.id == newItem.id -> ${oldItem.id == newItem.id}")
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CartEntity,
            newItem: CartEntity
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind()
    }

    fun setMinusListener(block: (Int) -> Unit) {
        minusListener = block
    }

    fun setDeleteListener(block: (CartEntity, Int) -> Unit) {
        deleteListener = block
    }

    fun setPlusListener(block: (Int) -> Unit) {
        plusListener = block
    }
}