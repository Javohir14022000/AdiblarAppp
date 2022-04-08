package com.example.adiblarappp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.adiblarappp.LibraryFragment
import com.example.adiblarappp.SaveFragment
import com.example.adiblarappp.SettingsFragment
import com.example.adiblarappp.WritersFragment

class ViewPagerAdapter( fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when(position){
            0 -> fragment = WritersFragment()
            1 -> fragment = LibraryFragment()
            2 -> fragment = SaveFragment()
        }
        return fragment
    }

}