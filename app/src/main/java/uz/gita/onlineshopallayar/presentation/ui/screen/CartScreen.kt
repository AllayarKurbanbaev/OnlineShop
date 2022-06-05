package uz.gita.onlineshopallayar.presentation.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity
import uz.gita.onlineshopallayar.databinding.ScreenCartBinding
import uz.gita.onlineshopallayar.presentation.ui.adapter.CartAdapter
import uz.gita.onlineshopallayar.presentation.viewmodel.CartViewModel
import uz.gita.onlineshopallayar.presentation.viewmodel.impl.CartViewModelImpl

@AndroidEntryPoint
class CartScreen : Fragment(R.layout.screen_cart) {

    private val binding by viewBinding(ScreenCartBinding::bind)
    private val viewModel: CartViewModel by viewModels<CartViewModelImpl>()
    private var bool = true

    private val adapter: CartAdapter by lazy { CartAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        listCart.adapter = adapter
        listCart.layoutManager = LinearLayoutManager(requireContext())

        viewModel.load()
//        swipeRefresh.setOnRefreshListener {
//            viewModel.load()
//        }

        adapter.setDeleteListener {
            Timber.tag("KKK").d(it.toString())
            if (bool) {
                viewModel.delete(it)
                bool = false
            }
        }

        adapter.setMinusListener {
            if (bool) {
                viewModel.minusClick(it)
                bool = false
            }
        }

        adapter.setPlusListener {
            if (bool) {
                viewModel.plusClick(it)
                bool = false
            }
        }

        order.setOnClickListener {
            if (bool) {
                viewModel.order()
                bool = false
            }
        }


        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        viewModel.messageLiveData.observe(viewLifecycleOwner, messageObserver)
        viewModel.orderLiveData.observe(viewLifecycleOwner, orderObserver)
        viewModel.deleteLiveData.observe(viewLifecycleOwner, deleteObserver)
    }

    private val deleteObserver = Observer<String> {

        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val orderObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val messageObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val loadObserver = Observer<List<CartEntity>> {
        Timber.tag("KKK").d("1")
        adapter.submitList(it)
        Timber.tag("KKK").d("2")
        bool = true
    }

    private val progressObserver = Observer<Boolean> {
//        binding.swipeRefresh.isRefreshing = it
    }

    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
}