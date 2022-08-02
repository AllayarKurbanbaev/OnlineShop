package uz.gita.onlineshopallayar.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.onlineshopallayar.R
import uz.gita.onlineshopallayar.app.App
import uz.gita.onlineshopallayar.databinding.ScreenMainBinding
import uz.gita.onlineshopallayar.presentation.ui.adapter.MainPageAdapter
import uz.gita.onlineshopallayar.presentation.viewmodel.MainViewModel
import uz.gita.onlineshopallayar.presentation.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {

    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private lateinit var adapter: MainPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.signOutLiveData.observe(this@MainScreen) { findNavController().navigate(R.id.signInScreen) }
        App.openDetailScreenLiveData.observe(this@MainScreen) {
            it?.let {
                findNavController().navigate(MainScreenDirections.actionMainScreenToDetailScreen(it))
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        adapter = MainPageAdapter(childFragmentManager, lifecycle)
        viewpagerMain.isUserInputEnabled = false
        viewpagerMain.adapter = adapter

        actionBar.logOut.setOnClickListener { viewModel.onClickSignOut() }
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    viewModel.onMenuSelected(0, it.title.toString())
                }
                else -> {
                    viewModel.onMenuSelected(1, it.title.toString())
                }
            }
            true
        }

        viewModel.navigateNextScreenLiveData.observe(viewLifecycleOwner, navigateNextScreenObserver)
        viewModel.messageLiveData.observe(viewLifecycleOwner, messageObserver)
        viewModel.toolbarTitleLiveData.observe(viewLifecycleOwner, toolbarTitleObserver)
    }

    private val toolbarTitleObserver = Observer<String> {
        binding.actionBar.actionBarTitle.text = it
    }

    private val messageObserver = Observer<String> {

    }
    private val navigateNextScreenObserver = Observer<Int> {
        binding.viewpagerMain.setCurrentItem(it, true)
    }
}