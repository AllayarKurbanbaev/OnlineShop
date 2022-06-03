package uz.gita.onlineshopallayar.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.onlineshopallayar.presentation.ui.screen.CartScreen
import uz.gita.onlineshopallayar.presentation.ui.screen.ProductScreen

class MainAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProductScreen()
            else -> CartScreen()
        }
    }

}