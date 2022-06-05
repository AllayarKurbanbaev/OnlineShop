package uz.gita.onlineshopallayar.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.data.remote.model.request.LoginRequest
import uz.gita.onlineshopallayar.databinding.ScreenSigninBinding
import uz.gita.onlineshopallayar.presentation.viewmodel.SignInViewModel
import uz.gita.onlineshopallayar.presentation.viewmodel.impl.SignInViewModelImpl
import uz.gita.onlineshopallayar.utils.showToast

@AndroidEntryPoint
class SignInScreen : Fragment(R.layout.screen_signin) {

    private val binding by viewBinding(ScreenSigninBinding::bind)
    private val viewModel: SignInViewModel by viewModels<SignInViewModelImpl>()

    private var username: Boolean = false
    private var password: Boolean = false

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        editTextUsername.addTextChangedListener {
            username = it.toString().isNotEmpty()
            check()
        }

        editTextPassword.addTextChangedListener {
            password = it.toString().isNotEmpty()
            check()
        }

        buttonLogin.setOnClickListener {
            viewModel.login(
                LoginRequest(
                    editTextUsername.text.toString(),
                    editTextPassword.text.toString()
                )
            )
        }

        viewModel.errorLiveData.observe(this@SignInScreen, errorObserver)
        viewModel.loginButtonLiveData.observe(viewLifecycleOwner, loginButtonObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        viewModel.openMainScreenLiveData.observe(this@SignInScreen, openMainScreenObserver)
    }

    private fun check() {
        binding.buttonLogin.isEnabled = username && password
    }

    private val openMainScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_signInScreen_to_mainScreen)
    }

    private val errorObserver = Observer<String> {
        showToast(it)
    }
    private val loginButtonObserver = Observer<String> {

    }
    private val progressObserver = Observer<Boolean> {
        when (it) {
            false -> binding.progress.hide()
            true -> binding.progress.show()
        }
    }
}