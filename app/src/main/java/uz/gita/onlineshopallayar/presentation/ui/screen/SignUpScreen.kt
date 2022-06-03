package uz.gita.onlineshopallayar.presentation.ui.screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.databinding.ScreenSingupBinding
import uz.gita.onlineshopallayar.presentation.viewmodel.SignUpViewModel
import uz.gita.onlineshopallayar.presentation.viewmodel.impl.SignUpViewModelImpl


@AndroidEntryPoint
class SignUpScreen : Fragment(R.layout.screen_singup) {

    private val binding by viewBinding(ScreenSingupBinding::bind)
    private val viewModel : SignUpViewModel by viewModels<SignUpViewModelImpl>()
}