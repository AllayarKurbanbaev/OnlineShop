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


class CartAdapter : ListAdapter<CartEntity, CartAdapter.CartViewHolder>(CartDiffUtil) {
    private var minusListener: ((Int) -> Unit)? = null
    private var plusListener: ((Int) -> Unit)? = null
    private var deleteListener: ((CartEntity) -> Unit)? = null

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            /*binding.root.setOnClickListener {
                Timber.tag("KKK").d("absoluteAdapterPosition = $absoluteAdapterPosition")
            }*/
            binding.buttonMinus.setOnClickListener {
                val model = getItem(absoluteAdapterPosition)
                if (model.quantity >= 1) {
                    minusListener?.invoke(model.id)
                }
            }

            binding.buttonAdd.setOnClickListener {
                val model = getItem(absoluteAdapterPosition)
                plusListener?.invoke(getItem(absoluteAdapterPosition).id)
            }

            binding.buttonDelete.setOnClickListener {
                Timber.tag("KKK").d("absoluteAdapterPosition = $absoluteAdapterPosition")
                deleteListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                binding.productName.text = productName
                binding.textPrice.text = productPrice.toString()
                binding.textPieces.text = quantity.toString()
                Glide.with(binding.productImage)
                    .load(image)
                    .placeholder(R.drawable.placeholder)
                    .into(binding.productImage)
            }
        }
    }

    private object CartDiffUtil : DiffUtil.ItemCallback<CartEntity>() {
        override fun areItemsTheSame(
            oldItem: CartEntity,
            newItem: CartEntity
        ): Boolean {
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
            /*ItemCartBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
            )*/
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind()
    }

    fun setMinusListener(block: (Int) -> Unit) {
        minusListener = block
    }

    fun setDeleteListener(block: (CartEntity) -> Unit) {
        deleteListener = block
    }

    fun setPlusListener(block: (Int) -> Unit) {
        plusListener = block
    }
}