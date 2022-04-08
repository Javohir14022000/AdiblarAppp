package com.example.adiblarappp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.adiblarappp.adapters.ViewPagerAdapter
import com.example.adiblarappp.databinding.FragmentHomeBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var viewPagerAdapter: ViewPagerAdapter
    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
            viewPager2.adapter = viewPagerAdapter
            bottomBar.onItemSelected = { position ->
                when (position) {
                    0 -> {
                        viewPager2.currentItem = 0
                    }
                    1 -> {
                        viewPager2.currentItem = 1
                    }
                    else -> {
                        viewPager2.currentItem = 2
                    }
                }
            }
            viewPager2.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> {
                            binding.bottomBar.itemActiveIndex = 0
                        }
                        1 -> {
                            binding.bottomBar.itemActiveIndex = 1
                        }
                        else -> {
                            binding.bottomBar.itemActiveIndex = 2
                        }
                    }
                }

                override fun onPageScrollStateChanged(state: Int) {

                }

            })
        }
    }

}