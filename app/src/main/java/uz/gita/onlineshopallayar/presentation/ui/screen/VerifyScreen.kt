package uz.gita.onlineshopallayar.presentation.ui.screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.databinding.ScreenVerifyBinding
import uz.gita.onlineshopallayar.presentation.viewmodel.VerifyViewModel
import uz.gita.onlineshopallayar.presentation.viewmodel.impl.VerifyViewModelImpl

@AndroidEntryPoint
class VerifyScreen : Fragment(R.layout.screen_verify) {

    private val binding by viewBinding(ScreenVerifyBinding::bind)
    private val viewModel: VerifyViewModel by viewModels<VerifyViewModelImpl>()


}