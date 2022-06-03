package uz.gita.onlineshopallayar.presentation.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity
import uz.gita.onlineshopallayar.data.remote.model.response.CartResponse
import uz.gita.onlineshopallayar.databinding.ScreenCartBinding
import uz.gita.onlineshopallayar.presentation.ui.adapter.CartAdapter
import uz.gita.onlineshopallayar.presentation.viewmodel.CartViewModel
import uz.gita.onlineshopallayar.presentation.viewmodel.impl.CartViewModelImpl

@AndroidEntryPoint
class CartScreen : Fragment(R.layout.screen_cart) {

    private val binding by viewBinding(ScreenCartBinding::bind)
    private val viewModel: CartViewModel by viewModels<CartViewModelImpl>()

    private val adapter : CartAdapter = CartAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        listCart.adapter = adapter
        viewModel.load()

        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
    }
    private val loadObserver = Observer<List<ProductResponseEntity>>{
        Timber.tag("BBB").d(it.toString())
        adapter.submitList(it)
    }

    private val progressObserver = Observer<Boolean>{

    }

    private val errorObserver = Observer<String>{
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
}