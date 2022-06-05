package uz.gita.onlineshopallayar.presentation.ui.page

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.app.App
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.databinding.ScreenAllProductsBinding
import uz.gita.onlineshopallayar.presentation.ui.adapter.ProductAdapter
import uz.gita.onlineshopallayar.presentation.viewmodel.ProductViewModel
import uz.gita.onlineshopallayar.presentation.viewmodel.impl.ProductViewModelImpl
import uz.gita.onlineshopallayar.utils.showToast


@AndroidEntryPoint
class ProductPage : Fragment(R.layout.screen_all_products) {

    private val binding by viewBinding(ScreenAllProductsBinding::bind)
    private val viewModel: ProductViewModel by viewModels<ProductViewModelImpl>()
    private val adapter: ProductAdapter = ProductAdapter()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        swipeRefresh.setOnRefreshListener { viewModel.loadData() }

        adapter.setOnButtonAddToCartListener { viewModel.addToCart(it) }
        adapter.setOnItemClickListener { App.openDetailScreen(it) }

        viewModel.addCartLiveData.observe(this@ProductPage, addCartObserver)
        viewModel.errorLiveData.observe(this@ProductPage, errorObserver)
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressLiveData)
    }

    private val progressLiveData = Observer<Boolean> {
        binding.swipeRefresh.isRefreshing = it
    }

    private val loadObserver = Observer<List<ProductData>> {
        adapter.submitList(it)
    }

    private val errorObserver = Observer<String> {
        showToast(it)
    }

    private val addCartObserver = Observer<String> {
        App.reloadCartPage()
    }
}