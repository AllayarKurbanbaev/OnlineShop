package uz.gita.onlineshopallayar.presentation.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.databinding.ScreenDetailBinding
import uz.gita.onlineshopallayar.presentation.viewmodel.DetailViewModel
import uz.gita.onlineshopallayar.presentation.viewmodel.impl.DetailViewModelImpl

@AndroidEntryPoint
class DetailScreen : Fragment(R.layout.screen_detail) {

    private val binding by viewBinding(ScreenDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels<DetailViewModelImpl>()
    private  var model : ProductData ?= null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        viewModel.loadData()

        swipeRefresh.setOnRefreshListener {
            viewModel.loadData()
        }

        backButton.setOnClickListener {
            viewModel.back()
        }
        addToCard.setOnClickListener {
            if(model != null){
            viewModel.addToCart(model!!)
            }
        }
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
        viewModel.addToCardLiveData.observe(viewLifecycleOwner, addToCardObserver)
        viewModel.onBackPressedLiveData.observe(viewLifecycleOwner, onBackPressedObserver)
    }

    private val onBackPressedObserver = Observer<Unit> {
        findNavController().popBackStack()
    }
    private val addToCardObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
    private val loadObserver = Observer<ProductData> {
        model = it
        binding.cost.text = it.price.toString()
        binding.title.text = it.title
        binding.tvDescription.text = it.description
        Glide.with(binding.imageView)
            .load(it.image)
            .placeholder(R.drawable.placeholder)
            .into(binding.imageView)
    }

    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val progressObserver = Observer<Boolean> {
        when (it) {
            false -> binding.swipeRefresh.isRefreshing = false
            else -> binding.swipeRefresh.isRefreshing = true
        }
    }
}