package uz.gita.onlineshopallayar.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity
import uz.gita.onlineshopallayar.databinding.ItemCartBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.TestViewHolder>() {
    private var minusListener: ((Int) -> Unit)? = null
    private var plusListener: ((Int) -> Unit)? = null
    private var deleteListener: ((CartEntity, Int) -> Unit)? = null

    var list: MutableList<CartEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class TestViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

            binding.buttonMinus.setOnClickListener {
                if (list[absoluteAdapterPosition].quantity > 1) {
                    minusListener?.invoke(absoluteAdapterPosition)
                }
            }

            binding.buttonAdd.setOnClickListener {
                plusListener?.invoke(absoluteAdapterPosition)
            }

            binding.buttonDelete.setOnClickListener {
                deleteListener?.invoke(
                    list[absoluteAdapterPosition],
                    absoluteAdapterPosition
                )
            }
        }

        fun bind() {
            list[absoluteAdapterPosition].apply {
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = list.size

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