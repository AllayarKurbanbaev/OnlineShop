package uz.gita.onlineshopallayar.presentation.ui.page

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.app.App
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity
import uz.gita.onlineshopallayar.databinding.ScreenCartBinding
import uz.gita.onlineshopallayar.presentation.ui.adapter.CartAdapter
import uz.gita.onlineshopallayar.presentation.ui.adapter.CartAdapterOld
import uz.gita.onlineshopallayar.presentation.viewmodel.CartViewModel
import uz.gita.onlineshopallayar.presentation.viewmodel.impl.CartViewModelImpl
import uz.gita.onlineshopallayar.utils.showToast

@AndroidEntryPoint
class CartPage : Fragment(R.layout.screen_cart) {
    private val binding by viewBinding(ScreenCartBinding::bind)
    private val viewModel: CartViewModel by viewModels<CartViewModelImpl>()
    private val cartAdapter: CartAdapter by lazy(LazyThreadSafetyMode.NONE) { CartAdapter() }
    private val cartAdapterOld: CartAdapterOld by lazy(LazyThreadSafetyMode.NONE) { CartAdapterOld() }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        listCart.adapter = cartAdapterOld
        listCart.layoutManager = LinearLayoutManager(requireContext())

        viewModel.load()

        cartAdapterOld.setDeleteListener { data, pos -> viewModel.delete(data, pos) }
        cartAdapterOld.setMinusListener { viewModel.minusClick(it) }
        cartAdapterOld.setPlusListener { viewModel.plusClick(it) }
        order.setOnClickListener {
            viewModel.order()
        }

        viewModel.errorLiveData.observe(this@CartPage, errorObserver)
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
        viewModel.messageLiveData.observe(this@CartPage, messageObserver)
        viewModel.orderLiveData.observe(this@CartPage, orderObserver)
        viewModel.deleteLiveData.observe(this@CartPage, deleteObserver)
        App.reloadCartPageLiveData.observe(viewLifecycleOwner, reloadObserver)
    }

    private val deleteObserver = Observer<String> {
        showToast(it)
    }

    private val orderObserver = Observer<String> {
        showToast(it)
    }

    private val messageObserver = Observer<String> {
        showToast(it)
    }

    private val loadObserver = Observer<List<CartEntity>> {
        cartAdapterOld.submitList(it)
        cartAdapterOld.notifyDataSetChanged()
    }

    private val errorObserver = Observer<String> {
        showToast(it)
    }
    private val reloadObserver = Observer<Unit> {
        viewModel.load()
    }
}