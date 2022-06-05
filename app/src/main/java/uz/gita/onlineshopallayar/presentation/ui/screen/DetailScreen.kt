package uz.gita.onlineshopallayar.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.databinding.ScreenDetailBinding
import uz.gita.onlineshopallayar.presentation.viewmodel.DetailViewModel
import uz.gita.onlineshopallayar.presentation.viewmodel.impl.DetailViewModelImpl
import uz.gita.onlineshopallayar.utils.showToast

@AndroidEntryPoint
class DetailScreen : Fragment(R.layout.screen_detail) {

    private val binding by viewBinding(ScreenDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels<DetailViewModelImpl>()
    private val args: DetailScreenArgs by navArgs()


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        describeData(args.data)

        backButton.setOnClickListener { viewModel.back() }
        addToCard.setOnClickListener { viewModel.addToCart(args.data) }

        viewModel.errorLiveData.observe(this@DetailScreen, errorObserver)
        viewModel.addToCardLiveData.observe(this@DetailScreen, addToCardObserver)
        viewModel.onBackPressedLiveData.observe(viewLifecycleOwner, onBackPressedObserver)
    }

    private val onBackPressedObserver = Observer<Unit> {
        findNavController().popBackStack()
    }
    private val addToCardObserver = Observer<String> {
        showToast(it)
        findNavController().popBackStack()
    }

    private val errorObserver = Observer<String> {
        showToast(it)
    }

    private fun describeData(data: ProductData) {
        binding.cost.text = "Price: ${data.price}$"
        binding.title.text = data.title
        binding.tvDescription.text = data.description
        Glide.with(binding.imageView)
            .load(data.image)
            .placeholder(R.drawable.placeholder)
            .into(binding.imageView)
    }

}