package uz.gita.onlineshopallayar.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.databinding.ScreenAllProductsBinding
import uz.gita.onlineshopallayar.presentation.ui.adapter.ProductAdapter
import uz.gita.onlineshopallayar.presentation.viewmodel.ProductViewModel
import uz.gita.onlineshopallayar.presentation.viewmodel.impl.ProductViewModelImpl


@AndroidEntryPoint
class ProductScreen : Fragment(R.layout.screen_all_products) {

    private val binding by viewBinding(ScreenAllProductsBinding::bind)
    private val viewModel: ProductViewModel by viewModels<ProductViewModelImpl>()
    private val adapter: ProductAdapter = ProductAdapter()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        recyclerView.adapter = adapter

        swipeRefresh.setOnRefreshListener {
            viewModel.loadData()
        }


        adapter.setOnButtonAddToCartListener {
            viewModel.addToCart(it)
        }

        adapter.setOnItemClickListener { productResponse, position ->
            viewModel.saveProductId(productResponse.id)
            viewModel.openDetail()
        }

        viewModel.addCartLiveData.observe(viewLifecycleOwner, addCartObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressLiveData)
        viewModel.openDetailScreenLiveData.observe(this@ProductScreen, openDetailObserver)

    }



    private val openDetailObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_mainScreen_to_detailScreen)
    }

    private val progressLiveData = Observer<Boolean> {
        when (it) {
            false -> binding.swipeRefresh.isRefreshing = false
            else -> binding.swipeRefresh.isRefreshing = true
        }
    }
    private val loadObserver = Observer<List<ProductData>> {
        adapter.submitList(it)
    }

    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val addCartObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
}