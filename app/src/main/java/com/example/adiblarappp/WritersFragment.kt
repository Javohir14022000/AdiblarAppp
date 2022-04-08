package com.example.adiblarappp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.red
import androidx.navigation.fragment.findNavController
import com.example.adiblarappp.adapters.WritersAdapter
import com.example.adiblarappp.databinding.FragmentWritersBinding
import com.example.adiblarappp.databinding.ItemTabBinding
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding


class WritersFragment : Fragment(R.layout.fragment_writers) {
    lateinit var writersAdapter: WritersAdapter
    lateinit var firebaseFirestore: FirebaseFirestore
    private val binding: FragmentWritersBinding by viewBinding(FragmentWritersBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            firebaseFirestore = FirebaseFirestore.getInstance()
            settings.setOnClickListener {
                findNavController().navigate(R.id.settingsFragment2)
            }

            writersAdapter = WritersAdapter(childFragmentManager)
            viewPager.adapter = writersAdapter
            tabLayout.setupWithViewPager(binding.viewPager)

            setTabs()
            searchClick()

        }
    }

    private fun searchClick() {
        binding.search.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }
    }

    private fun setTabs() {
        for (i in 0 until binding.tabLayout.tabCount) {

            val tabBind = ItemTabBinding.inflate(LayoutInflater.from(context),null,false)
//            val itemTabBinding = ItemTabBinding.inflate(layoutInflater)
            val tab = binding.tabLayout.getTabAt(i)
         tab?.customView = tabBind.titleTv
            when (i) {

                0 -> tabBind.titleTv.text = "Mumtoz adabiyoti"
                1 -> tabBind.titleTv.text = "O'zbek adabiyoti"
                2 -> tabBind.titleTv.text = "Jahon adabiyoti"
            }
            if (i == 0) {
                tabBind.titleTv.setBackgroundResource(R.drawable.tab_item_back_selected)
//                tabBind.titleTv.setTextColor(resources.getColor(R.color.white))
            } else {
                tabBind.titleTv.setBackgroundResource(R.drawable.tab_item_back_unselected)
//                tabBind.titleTv.setTextColor(resources.getColor(R.color.tab_unselected_text_color))
            }
        }

        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

//                val itemTabBinding = ItemTabBinding.inflate(layoutInflater)
//                tab?.customView = itemTabBinding.titleTv
//                val itemTabBinding = tab?.customView as ItemTabBinding
//                val view = tab.customView as ItemTabBinding
                tab?.customView?.setBackgroundResource(R.drawable.tab_item_back_selected)

//                tab?.customView?.setTextColor(resources.getColor(R.color.white))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                val itemTabBinding = tab?.customView as ItemTabBinding
               tab?.customView?.setBackgroundResource(R.drawable.tab_item_back_unselected)
//                itemTabBinding.titleTv.setTextColor(resources.getColor(R.color.tab_unselected_text_color))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
}