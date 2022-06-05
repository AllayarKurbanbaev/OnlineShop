package uz.gita.onlineshopallayar.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.databinding.ScreenSplashBinding
import uz.gita.onlineshopallayar.presentation.viewmodel.SplashViewModel
import uz.gita.onlineshopallayar.presentation.viewmodel.impl.SplashViewModelImpl

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {

    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openNextScreenLiveData.observe(this@SplashScreen, openNextObserver)
    }

    private val openNextObserver = Observer<Boolean> {
        if (it){
            findNavController().navigate(R.id.action_splashScreen_to_mainScreen)
        } else {
            findNavController().navigate(R.id.action_splashScreen_to_signInScreen)
        }
    }

}